package com.example.employee.exception;

public class EmployeeNotFoundException extends EmployeeException {

    public EmployeeNotFoundException(Long id) {
        super(EmployeeErrorCode.EMPLOYEE_NOT_FOUND,
                "Employee not found for id=" + id);
    }

    public EmployeeNotFoundException(String email) {
        super(EmployeeErrorCode.EMPLOYEE_NOT_FOUND,
                "Employee not found for email=" + email);
    }
}
