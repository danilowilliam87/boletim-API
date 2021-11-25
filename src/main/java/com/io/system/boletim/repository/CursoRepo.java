package com.io.system.boletim.repository;

import com.io.system.boletim.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepo extends JpaRepository<Curso, Long> {
    public Optional<Curso> findCursoByNome(String nome);
}
