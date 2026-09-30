package com.example.employee.exception;

import com.example.core.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum EmployeeErrorCode implements ErrorCode {
    EMPLOYEE_NOT_FOUND      ("EMP_404", "Employee not found",             HttpStatus.NOT_FOUND),
    EMPLOYEE_EMAIL_EXISTS   ("EMP_409", "Employee email already exists",  HttpStatus.CONFLICT),
    EMPLOYEE_CODE_EXISTS    ("EMP_410", "Employee code already exists",   HttpStatus.CONFLICT),
    EMPLOYEE_INACTIVE       ("EMP_403", "Employee is inactive",           HttpStatus.FORBIDDEN),
    INVALID_DEPARTMENT      ("EMP_422", "Invalid department",             HttpStatus.UNPROCESSABLE_ENTITY),
    INVALID_SALARY          ("EMP_423", "Invalid salary",                 HttpStatus.UNPROCESSABLE_ENTITY);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    EmployeeErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override public String getCode()           { return code; }
    @Override public String getMessage()        { return message; }
    @Override public HttpStatus getHttpStatus() { return httpStatus; }
}
