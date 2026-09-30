package com.example.auth.exception;

public class EmailAlreadyExistsException extends AuthException {
    public EmailAlreadyExistsException(String email) {
        super(AuthErrorCode.EMAIL_ALREADY_EXISTS,
                "Email already registered: " + email);
    }
}
