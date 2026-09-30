package com.example.auth.exception;

public class AccountExpiredException extends AuthException {
    public AccountExpiredException(String username) {
        super(AuthErrorCode.ACCOUNT_EXPIRED,
                "Account has expired: " + username);
    }
}
