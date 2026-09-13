package com.example.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // generic
    INTERNAL_ERROR("SYS_001", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_FAILED("SYS_002", "Validation failed", HttpStatus.BAD_REQUEST),
    BAD_REQUEST("SYS_003", "Bad request", HttpStatus.BAD_REQUEST),

    // resource
    RESOURCE_NOT_FOUND("RES_001", "Resource not found", HttpStatus.NOT_FOUND),
    RESOURCE_ALREADY_EXISTS("RES_002", "Resource already exists", HttpStatus.CONFLICT),

    // auth
    UNAUTHORIZED("AUTH_001", "Unauthorized", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("AUTH_002", "Forbidden", HttpStatus.FORBIDDEN),
    INVALID_CREDENTIALS("AUTH_003", "Invalid credentials", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED("AUTH_004", "Token expired", HttpStatus.UNAUTHORIZED),

    // domain-specific (per module)
    MERCHANT_NOT_FOUND("MER_001", "Merchant not found", HttpStatus.NOT_FOUND),
    TERMINAL_NOT_FOUND("TRM_001", "Terminal not found", HttpStatus.NOT_FOUND),
    EMPLOYEE_NOT_FOUND("EMP_001", "Employee not found", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

}
