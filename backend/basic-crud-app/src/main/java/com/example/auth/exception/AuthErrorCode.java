package com.example.auth.exception;

import com.example.core.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum AuthErrorCode implements ErrorCode {

    /* --- Registration / user --- */
    USER_ALREADY_EXISTS       ("AUTH_409_USER_EXISTS",   "User already exists",                 HttpStatus.CONFLICT),
    USERNAME_ALREADY_EXISTS   ("AUTH_409_USERNAME",      "Username already taken",              HttpStatus.CONFLICT),
    EMAIL_ALREADY_EXISTS      ("AUTH_409_EMAIL",         "Email already registered",            HttpStatus.CONFLICT),

    /* --- Login --- */
    INVALID_CREDENTIALS       ("AUTH_401_CREDENTIALS",   "Invalid username or password",        HttpStatus.UNAUTHORIZED),
    ACCOUNT_DISABLED          ("AUTH_403_DISABLED",      "Account is disabled",                 HttpStatus.FORBIDDEN),
    ACCOUNT_LOCKED            ("AUTH_403_LOCKED",        "Account is locked",                   HttpStatus.FORBIDDEN),
    ACCOUNT_EXPIRED           ("AUTH_403_EXPIRED",       "Account has expired",                 HttpStatus.FORBIDDEN),
    CREDENTIALS_EXPIRED       ("AUTH_403_CREDS_EXPIRED", "Credentials have expired",            HttpStatus.FORBIDDEN),
    EMAIL_NOT_VERIFIED        ("AUTH_403_EMAIL_VERIFY",  "Email address not verified",          HttpStatus.FORBIDDEN),

    /* --- JWT --- */
    TOKEN_MISSING             ("AUTH_401_TOKEN_MISSING", "Authentication token is missing",     HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID             ("AUTH_401_TOKEN_INVALID", "Authentication token is invalid",     HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED             ("AUTH_401_TOKEN_EXPIRED", "Authentication token has expired",    HttpStatus.UNAUTHORIZED),
    TOKEN_REVOKED             ("AUTH_401_TOKEN_REVOKED", "Authentication token was revoked",    HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_INVALID     ("AUTH_401_REFRESH",       "Refresh token is invalid or expired", HttpStatus.UNAUTHORIZED),

    /* --- Authorization --- */
    ACCESS_DENIED             ("AUTH_403_ACCESS_DENIED", "Access denied",                       HttpStatus.FORBIDDEN),
    ROLE_NOT_FOUND            ("AUTH_404_ROLE",          "Role not found",                      HttpStatus.NOT_FOUND),

    /* --- Password --- */
    PASSWORD_TOO_WEAK         ("AUTH_422_PWD_WEAK",      "Password does not meet requirements", HttpStatus.UNPROCESSABLE_ENTITY),
    PASSWORD_MISMATCH         ("AUTH_422_PWD_MISMATCH",  "Passwords do not match",              HttpStatus.UNPROCESSABLE_ENTITY),
    OLD_PASSWORD_MISMATCH     ("AUTH_422_OLD_PWD",       "Old password is incorrect",           HttpStatus.UNPROCESSABLE_ENTITY);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    AuthErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override public String getCode()           { return code; }
    @Override public String getMessage()        { return message; }
    @Override public HttpStatus getHttpStatus() { return httpStatus; }
}
