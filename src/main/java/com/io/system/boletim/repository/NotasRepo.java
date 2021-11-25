package com.io.system.boletim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotasRepo extends JpaRepository<NotasRepo, Long> {
}
