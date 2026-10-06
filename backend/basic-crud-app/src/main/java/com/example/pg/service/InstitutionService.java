package com.example.pg.service;

import com.example.pg.entity.Institution;

public interface InstitutionService {

    Institution fetchInstitutionById(long instId);
}
