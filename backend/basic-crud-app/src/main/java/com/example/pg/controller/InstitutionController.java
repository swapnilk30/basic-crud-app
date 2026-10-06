package com.example.pg.controller;

import com.example.pg.entity.Institution;
import com.example.pg.service.InstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/institutions")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping("/{id}")
    public Institution getById(@PathVariable long id) {
        return institutionService.fetchInstitutionById(id);
    }
}
