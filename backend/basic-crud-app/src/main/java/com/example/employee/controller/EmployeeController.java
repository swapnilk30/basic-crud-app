package com.example.employee.controller;

import com.example.employee.dto.request.EmployeeRequest;
import com.example.employee.dto.response.EmployeeUploadResponse;
import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeRequest request) {

        return ResponseEntity.ok(employeeService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {

        log.info("Fetching all employees");
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }


    @PostMapping(value = "/upload-csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EmployeeUploadResponse> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(employeeService.importFromCsv(file));
    }
}
