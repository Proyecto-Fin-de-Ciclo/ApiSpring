package com.cdh.apitaller.repository;

import com.cdh.apitaller.entitys.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findAllByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

}
