package com.example.employee.exception;

import com.example.core.exception.BusinessException;

public class EmployeeException extends BusinessException {

    public EmployeeException(EmployeeErrorCode code) {
        super(code);
    }

    public EmployeeException(EmployeeErrorCode code, String message) {
        super(code, message);
    }

    public EmployeeException(EmployeeErrorCode code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
