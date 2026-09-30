package com.example.core.enums;

import com.example.core.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum CoreErrorCode implements ErrorCode {

    BAD_REQUEST       ("CORE_400", "Bad request",         HttpStatus.BAD_REQUEST),
    VALIDATION_FAILED ("CORE_400", "Validation failed", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED      ("CORE_401", "Unauthorized",      HttpStatus.UNAUTHORIZED),
    FORBIDDEN         ("CORE_403", "Forbidden",         HttpStatus.FORBIDDEN),
    RESOURCE_NOT_FOUND("CORE_404", "Resource not found",HttpStatus.NOT_FOUND),
    CONFLICT          ("CORE_409", "Conflict",          HttpStatus.CONFLICT),
    INTERNAL_ERROR    ("CORE_500", "Internal error",    HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    CoreErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override public String getCode()           { return code; }
    @Override public String getMessage()        { return message; }
    @Override public HttpStatus getHttpStatus() { return httpStatus; }
}
