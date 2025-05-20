package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.ReparacionDTO;
import com.cdh.apitaller.entitys.Reparacion;

import java.util.List;

public interface ReparacionService {
    void add(ReparacionDTO reparacionDTO);
    void update(ReparacionDTO reparacionDTO);
    void delete(ReparacionDTO reparacionDTO);
    Object findById(Long id);
    List<Reparacion> findAll();
}
