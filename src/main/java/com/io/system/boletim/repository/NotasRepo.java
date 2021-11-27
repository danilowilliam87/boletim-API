package com.io.system.boletim.repository;

import com.io.system.boletim.domain.Aluno;
import com.io.system.boletim.domain.Disciplina;
import com.io.system.boletim.domain.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotasRepo extends JpaRepository<Notas, Long> {
   public Optional<Notas> findNotasByDisciplinaAndAluno(Disciplina disciplina, Aluno aluno);
   public Optional<Notas> findNotasBySemestreAndDisciplina(String semestre, Disciplina disciplina);
   public Optional<Notas> findNotasByAlunoAndSemestre(Long idAluno, String semestre);
   public List<Notas> findAllByAlunoAndSemestre(Aluno aluno, String semestre);
}
