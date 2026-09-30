package com.example.pg.exception;

public class TerminalNotFoundException extends PgException {

    public TerminalNotFoundException(String tranportalId) {
        super(PgErrorCode.TERMINAL_NOT_FOUND,
                "Terminal not found for tranportalId=" + tranportalId);
    }
}
