package com.cdh.apitaller.repository;

import com.cdh.apitaller.entitys.Reparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReparacionRepository extends JpaRepository<Reparacion, Long> {
    List<Reparacion> findByUserId(Long clienteId);

    @Query("SELECT r FROM Reparacion r WHERE (:inicio IS NULL OR r.horaInicio >= :inicio) AND (:fin IS NULL OR r.horaInicio <= :fin)")
    List<Reparacion> findByHoraInicioBetween(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );
}

