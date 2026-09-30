package com.example.employee.exception;

public class EmployeeInactiveException extends EmployeeException {
    public EmployeeInactiveException(Long id) {
        super(EmployeeErrorCode.EMPLOYEE_INACTIVE,
                "Employee is inactive: id=" + id);
    }
}
