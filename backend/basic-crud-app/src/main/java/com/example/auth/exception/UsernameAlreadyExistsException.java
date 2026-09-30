package com.example.auth.exception;

public class UsernameAlreadyExistsException extends AuthException {
    public UsernameAlreadyExistsException(String username) {
        super(AuthErrorCode.USERNAME_ALREADY_EXISTS,
                "Username already taken: " + username);
    }
}
