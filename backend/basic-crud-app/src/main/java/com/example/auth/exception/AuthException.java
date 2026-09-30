package com.example.auth.exception;

import com.example.core.exception.BusinessException;

public class AuthException extends BusinessException {

    public AuthException(AuthErrorCode code) {
        super(code);
    }

    public AuthException(AuthErrorCode code, String message) {
        super(code, message);
    }

    public AuthException(AuthErrorCode code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
