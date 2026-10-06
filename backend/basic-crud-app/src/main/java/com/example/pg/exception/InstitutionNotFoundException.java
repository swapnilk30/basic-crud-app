package com.example.pg.exception;

public class InstitutionNotFoundException extends PgException {

    public InstitutionNotFoundException(String institutionId) {
        super(PgErrorCode.INSTITUTION_NOT_FOUND,
                "Institution not found for institutionId=" + institutionId);
    }
}
