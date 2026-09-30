package com.example.auth.exception;

public class UserAlreadyExistsException extends AuthException {

    public UserAlreadyExistsException(String username) {
        super(AuthErrorCode.USER_ALREADY_EXISTS,
                "User already exists: " + username);
    }

    public UserAlreadyExistsException(String field, String value) {
        super(AuthErrorCode.USER_ALREADY_EXISTS,
                "User already exists with " + field + "=" + value);
    }
}
