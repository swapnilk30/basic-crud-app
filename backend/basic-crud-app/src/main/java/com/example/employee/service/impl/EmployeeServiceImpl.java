package com.example.employee.service.impl;


import com.example.employee.dto.request.EmployeeRequest;
import com.example.employee.dto.response.EmployeeUploadResponse;
import com.example.employee.entity.Employee;
import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee create(EmployeeRequest request) {
        log.info("Creating employee with request: {}", request);

        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setJoiningDate(request.getJoiningDate());
        employee.setResignationDate(request.getResignationDate());

        employeeRepository.save(employee);

        log.info("Employee created successfully: {}", employee);
        return employee;
    }

    @Override
    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @Override
    @Transactional
    public EmployeeUploadResponse importFromCsv(MultipartFile file) {

        List<String> errors = new ArrayList<>();
        List<Employee> toSave = new ArrayList<>();
        Set<String> seenEmails = new HashSet<>();

        int rowNum = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.builder()
                     .setHeader()
                     .setSkipHeaderRecord(true)
                     .setTrim(true)
                     .setIgnoreEmptyLines(true)
                     .build())) {

            for (CSVRecord rec : parser) {
                rowNum++;

                String name  = rec.isSet("name")  ? rec.get("name")  : null;
                String email = rec.isSet("email") ? rec.get("email") : null;

                // --- validations ---
                if (name == null || name.isBlank()) {
                    errors.add("Row " + rowNum + ": name is required");
                    continue;
                }
                if (email == null || email.isBlank() || !email.contains("@")) {
                    errors.add("Row " + rowNum + ": invalid email '" + email + "'");
                    continue;
                }
                if (!seenEmails.add(email)) {
                    errors.add("Row " + rowNum + ": duplicate email in file '" + email + "'");
                    continue;
                }
                if (employeeRepository.findByEmail(email).isPresent()) {
                    errors.add("Row " + rowNum + ": email already exists '" + email + "'");
                    continue;
                }

                // --- parse dates + build entity, catching date errors per-row ---
                try {
                    LocalDate dob  = parseDate(rec, "dateOfBirth");
                    LocalDate join = parseDate(rec, "joiningDate");
                    LocalDate res  = parseDate(rec, "resignationDate");

                    if (join != null && res != null && res.isBefore(join)) {
                        errors.add("Row " + rowNum + ": resignation date before joining date");
                        continue;
                    }

                    toSave.add(Employee.builder()
                            .name(name)
                            .email(email)
                            .phone(rec.isSet("phone") ? rec.get("phone") : null)
                            .dateOfBirth(dob)
                            .joiningDate(join)
                            .resignationDate(res)
                            .build());

                } catch (DateTimeParseException e) {
                    errors.add("Row " + rowNum + ": " + e.getMessage());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file: " + e.getMessage(), e);
        }

        if (!toSave.isEmpty()) {
            employeeRepository.saveAll(toSave);
        }

        return new EmployeeUploadResponse(
                rowNum - 1,           // totalRows = data rows (excluding header)
                toSave.size(),        // successCount
                errors.size(),        // failureCount
                errors);
    }

    private LocalDate parseDate(CSVRecord rec, String col) {
        if (!rec.isSet(col)) return null;
        String v = rec.get(col);
        if (v == null || v.isBlank()) return null;
        try {
            return LocalDate.parse(v.trim(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(
                    "invalid " + col + " '" + v + "' (expected yyyy-MM-dd)", v, 0);
        }
    }
}
