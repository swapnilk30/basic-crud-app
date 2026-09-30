package com.example.auth.exception;

public class AccountLockedException extends AuthException {
    public AccountLockedException(String username) {
        super(AuthErrorCode.ACCOUNT_LOCKED,
                "Account is locked: " + username);
    }
}
