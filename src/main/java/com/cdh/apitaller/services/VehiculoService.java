package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.VehiculoDTO;

import java.util.List;

public interface VehiculoService {
    void addVehiculo(VehiculoDTO vehiculoDTO);
    void updateVehiculo(VehiculoDTO vehiculoDTO);
    void deleteVehiculo(VehiculoDTO vehiculoDTO);
    Object findById(Long id);
    List<Object> findAll();
}
