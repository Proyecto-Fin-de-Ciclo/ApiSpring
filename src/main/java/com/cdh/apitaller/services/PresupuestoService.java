package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.PresupuestoDTO;
import com.cdh.apitaller.dtos.ReparacionDTO;
import com.cdh.apitaller.entitys.Presupuesto;

import java.time.LocalDateTime;
import java.util.List;

public interface PresupuestoService {
    void add(ReparacionDTO reparacionDTO, String matricula,String descripcion);

    void update(PresupuestoDTO presupuestoDTO);

    void delete(Long id);

    Presupuesto findById(Long id);

    List<Presupuesto> findAll();

}
