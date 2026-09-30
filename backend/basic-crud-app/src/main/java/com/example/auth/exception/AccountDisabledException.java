package com.example.auth.exception;

public class AccountDisabledException extends AuthException {
    public AccountDisabledException(String username) {
        super(AuthErrorCode.ACCOUNT_DISABLED,
                "Account is disabled: " + username);
    }
}
