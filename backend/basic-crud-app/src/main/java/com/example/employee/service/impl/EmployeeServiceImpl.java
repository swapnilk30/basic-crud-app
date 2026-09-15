package com.example.employee.service.impl;

import com.example.core.exception.ErrorCode;
import com.example.core.exception.ResourceNotFoundException;
import com.example.employee.dto.request.EmployeeRequest;
import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.EMPLOYEE_NOT_FOUND,
                        "Employee not found with id: " + id
                ));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
