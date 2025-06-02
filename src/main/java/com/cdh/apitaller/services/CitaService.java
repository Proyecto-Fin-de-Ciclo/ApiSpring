package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.CitaDTO;
import com.cdh.apitaller.entitys.Cita;
import com.cdh.apitaller.entitys.CompaniaAseguradora;
import com.cdh.apitaller.entitys.User;
import com.cdh.apitaller.entitys.Vehiculo;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {
    void add(CitaDTO citaDTO);
    void update(CitaDTO citaDTO);
    void delete(Long id);
    Cita findById(Long id);
    List<Cita> findAll();
    List<Cita> findAllByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    User findByUsername(String username);
    Vehiculo findByIdVehiculo(Long id);
}
