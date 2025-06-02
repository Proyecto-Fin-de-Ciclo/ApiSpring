package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.OrdenDeTrabajoDTO;
import com.cdh.apitaller.dtos.ReparacionDTO;
import com.cdh.apitaller.entitys.Reparacion;

import java.time.LocalDateTime;
import java.util.List;

public interface ReparacionService {
    void add(ReparacionDTO reparacionDTO);
    void update(ReparacionDTO reparacionDTO);
    void delete(Long id);
    Reparacion findById(Long id);
    List<Reparacion> findAll();
    List<Reparacion> findByUserId(Long id);
    List<Reparacion> findByFecha(LocalDateTime inicio, LocalDateTime fin);
    Reparacion updateEstado(Long id, String estado);
    OrdenDeTrabajoDTO addOrdenDeTrabajo(ReparacionDTO reparacionDTO, String descripcion,String matricula);
    Reparacion dtoToEntity(ReparacionDTO reparacionDTO);
    Reparacion updateFinalTime(Reparacion reparacion);
    void updateFechaFinalReparacion(Long id, LocalDateTime fechaFinalReparacion);
    Reparacion obtenerReparacionActivaConPresupuestoAceptado(Long userId, Long vehiculoId);
}
