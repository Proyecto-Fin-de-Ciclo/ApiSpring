package com.cdh.apitaller.repository;

import com.cdh.apitaller.entitys.OrdenDeTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenDeTrabajoRepository extends JpaRepository<OrdenDeTrabajo, Long> {

    @Query("SELECT o FROM OrdenDeTrabajo o JOIN o.trabajadores t WHERE t.id = :trabajadorId")
    List<OrdenDeTrabajo> findByTrabajadorId(@Param("trabajadorId") Long trabajadorId);

}
