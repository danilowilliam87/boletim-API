package com.io.system.boletim.repository;

import com.io.system.boletim.domain.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepo extends JpaRepository<Disciplina, Long> {
}
