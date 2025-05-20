package com.cdh.apitaller.repository;

import com.cdh.apitaller.entitys.Cita;
import com.cdh.apitaller.entitys.Reparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparacionRepository extends JpaRepository<Reparacion, Long> {
}
