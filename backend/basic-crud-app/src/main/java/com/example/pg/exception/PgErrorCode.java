package com.example.pg.exception;

import com.example.core.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum PgErrorCode implements ErrorCode {

    TERMINAL_NOT_FOUND    ("PG_TERMINAL_NOT_FOUND",    "Terminal not found",            HttpStatus.NOT_FOUND),
    TERMINAL_NOT_ENABLED  ("PG_TERMINAL_NOT_ENABLED",  "Terminal is not enabled",       HttpStatus.FORBIDDEN),
    MERCHANT_NOT_FOUND    ("PG_MERCHANT_NOT_FOUND",    "Merchant not found",            HttpStatus.NOT_FOUND),
    PAYMENT_DECLINED      ("PG_PAYMENT_DECLINED",      "Payment was declined",          HttpStatus.UNPROCESSABLE_ENTITY),
    DUPLICATE_TRANSACTION ("PG_DUPLICATE_TRANSACTION", "Duplicate transaction",         HttpStatus.CONFLICT),
    PROCESSOR_UNAVAILABLE ("PG_PROCESSOR_UNAVAILABLE", "Payment processor unavailable", HttpStatus.BAD_GATEWAY);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    PgErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override public String getCode()           { return code; }
    @Override public String getMessage()        { return message; }
    @Override public HttpStatus getHttpStatus() { return httpStatus; }
}
