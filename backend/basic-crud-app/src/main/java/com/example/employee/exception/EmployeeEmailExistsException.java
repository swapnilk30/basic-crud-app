package com.example.employee.exception;

public class EmployeeEmailExistsException extends EmployeeException {

    public EmployeeEmailExistsException(String email) {
        super(EmployeeErrorCode.EMPLOYEE_EMAIL_EXISTS,
                "Employee email already exists: " + email);
    }
}
