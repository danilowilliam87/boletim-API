package com.io.system.boletim.repository;

import com.io.system.boletim.domain.Curso;
import com.io.system.boletim.domain.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepo extends JpaRepository<Curso, Long> {
    public Optional<Curso> findCursoByNomeEquals(String nome);

}
