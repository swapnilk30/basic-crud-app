package com.example.pg.exception;

import com.example.core.exception.BusinessException;

public class PgException extends BusinessException {

    public PgException(PgErrorCode code)                 { super(code); }
    public PgException(PgErrorCode code, String message) { super(code, message); }
    public PgException(PgErrorCode code, String message, Throwable cause) {
        super(code, message, cause);
    }

}
