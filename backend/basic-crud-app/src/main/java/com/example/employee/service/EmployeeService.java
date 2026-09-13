package com.example.employee.service;

import com.example.employee.dto.request.EmployeeRequest;
import com.example.employee.entity.Employee;

public interface EmployeeService {

    Employee create(EmployeeRequest request);
}
