package com.io.system.boletim.repository;

import com.io.system.boletim.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepo extends JpaRepository<Aluno, Long> {

    public Optional<Aluno> findAlunoByEmail(String email);
    public Optional<Aluno> findAlunoByNomeEquals(String nome);
}
