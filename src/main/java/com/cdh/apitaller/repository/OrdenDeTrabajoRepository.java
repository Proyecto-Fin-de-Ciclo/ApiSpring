package com.cdh.apitaller.repository;

import com.cdh.apitaller.entitys.OrdenDeTrabajo;
import com.cdh.apitaller.entitys.Pieza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDeTrabajoRepository extends JpaRepository<OrdenDeTrabajo, Long> {
}
