package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.CitaDTO;
import com.cdh.apitaller.entitys.Cita;

import java.util.List;

public interface CitaService {
    void add(CitaDTO citaDTO);
    void update(CitaDTO citaDTO);
    void delete(CitaDTO citaDTO);
    Cita findById(Long id);
    List<Cita> findAll();
}
