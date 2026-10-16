package com.example.employee.service;

import com.example.employee.dto.request.EmployeeRequest;
import com.example.employee.dto.response.EmployeeUploadResponse;
import com.example.employee.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    Employee create(EmployeeRequest request);

    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployees();



    EmployeeUploadResponse importFromCsv(MultipartFile file);

}
