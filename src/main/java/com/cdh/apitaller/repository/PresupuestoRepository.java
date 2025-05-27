package com.cdh.apitaller.repository;

import com.cdh.apitaller.entitys.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresupuestoRepository extends JpaRepository<Presupuesto, Long> {
}
