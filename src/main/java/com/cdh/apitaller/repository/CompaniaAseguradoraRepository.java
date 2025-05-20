package com.cdh.apitaller.repository;

import com.cdh.apitaller.entitys.Cita;
import com.cdh.apitaller.entitys.CompaniaAseguradora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaniaAseguradoraRepository extends JpaRepository<CompaniaAseguradora, Long> {
}
