package com.io.system.boletim.repository;

import com.io.system.boletim.domain.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaRepo extends JpaRepository<Disciplina, Long> {

    public Optional<Disciplina>findDisciplinaByNomeLike(String nome);
    public List<Optional<Disciplina>>findDisciplinaByNome(String nome);
    public Optional<Disciplina> findDisciplinaByNomeEquals(String nome);
}
