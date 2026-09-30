package com.example.employee.exception;

public class EmployeeCodeExistsException extends EmployeeException {

    public EmployeeCodeExistsException(String code) {
        super(EmployeeErrorCode.EMPLOYEE_CODE_EXISTS,
                "Employee code already exists: " + code);
    }
}
