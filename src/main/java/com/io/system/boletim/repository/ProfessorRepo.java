package com.io.system.boletim.repository;

import com.io.system.boletim.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepo extends JpaRepository<Professor, Long> {
    public Optional<Professor>findProfessorByEmail(String email);
}
