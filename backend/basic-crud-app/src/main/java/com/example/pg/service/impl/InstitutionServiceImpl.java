package com.example.pg.service.impl;

import com.example.pg.entity.Institution;
import com.example.pg.exception.InstitutionNotFoundException;
import com.example.pg.repository.InstitutionRepository;
import com.example.pg.service.InstitutionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;


    @Override
    public Institution fetchInstitutionById(long instId) {
        return institutionRepository.findByInstitutionId(instId).orElseThrow(() -> new InstitutionNotFoundException(String.valueOf(instId)));
    }
}
