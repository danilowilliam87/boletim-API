package com.io.system.boletim.repository;

import com.io.system.boletim.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepo extends JpaRepository<Professor, Long> {
    public Optional<Professor>findProfessorByEmail(String email);

    @Modifying
    @Query(value = "DELETE FROM DISCIPLINA_PROFESSOR WHERE ID_PROFESSOR = ?1", nativeQuery = true)
    public void deleteProfessorJOinTable(@Param("id") Long id);
}
