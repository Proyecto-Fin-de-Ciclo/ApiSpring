package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.VehiculoDTO;
import com.cdh.apitaller.entitys.Vehiculo;

import java.util.List;

public interface VehiculoService {
    void addVehiculo(VehiculoDTO vehiculoDTO);
    void updateVehiculo(VehiculoDTO vehiculoDTO);
    void deleteVehiculo(Long id);
    Vehiculo findById(Long id);
    List<Vehiculo> findAll();
}
