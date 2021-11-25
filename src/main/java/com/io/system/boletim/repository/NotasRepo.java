package com.io.system.boletim.repository;

import com.io.system.boletim.domain.Disciplina;
import com.io.system.boletim.domain.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotasRepo extends JpaRepository<Notas, Long> {

   @Query(value = "select n from NOTAS n where n.disciplinas.nome = ?1 and n.semestre = ?2")
   public Optional<Notas> findByDisciplinaAndSemestre(@Param("NOME") String nomeDisciplina,
                                                      @Param("SEMESTRE") String semestre);
}
