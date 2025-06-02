package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.OrdenDeTrabajoDTO;
import com.cdh.apitaller.dtos.PiezaDTO;
import com.cdh.apitaller.entitys.OrdenDeTrabajo;
import com.cdh.apitaller.entitys.Pieza;
import com.cdh.apitaller.entitys.Trabajador;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdenDeTrabajoService {
    void add(OrdenDeTrabajoDTO ordenDeTrabajoDTO);
    void update(OrdenDeTrabajoDTO ordenDeTrabajoDTO);
    void delete(Long id);
    OrdenDeTrabajo findById(Long id);
    List<OrdenDeTrabajo> findAll();
    void updateFechaFinalReparacion(Long id, LocalDateTime fechaFinalReparacion,OrdenDeTrabajoDTO ordenDeTrabajoDTO);
    List<OrdenDeTrabajo> findByTrabajadorId(Long id);
}
