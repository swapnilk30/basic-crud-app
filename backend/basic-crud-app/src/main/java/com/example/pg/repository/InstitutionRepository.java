package com.example.pg.repository;

import com.example.pg.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    Optional<Institution> findByInstitutionId(Long institutionId);
    //Optional<Institution> findByIdAndStatusNot(Long id, String status);
}
