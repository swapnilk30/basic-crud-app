package com.example.pg.repository;

import com.example.pg.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TerminalRepository extends JpaRepository<Terminal, Long> {

    Optional<Terminal> findByTranportalId(String tranportalId);
}
