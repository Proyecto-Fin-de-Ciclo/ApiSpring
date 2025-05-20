package com.cdh.apitaller.services.impl;

import com.cdh.apitaller.dtos.VehiculoDTO;
import com.cdh.apitaller.entitys.Vehiculo;
import com.cdh.apitaller.mappers.VehiculoMapper;
import com.cdh.apitaller.repository.VehiculoRepository;
import com.cdh.apitaller.services.VehiculoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehiculoServiceImpl  implements VehiculoService {
    private final VehiculoRepository vehiculoRepository;
    private final VehiculoMapper vehiculoMapper;
    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, VehiculoMapper vehiculoMapper) {
        this.vehiculoRepository = vehiculoRepository;
        this.vehiculoMapper = vehiculoMapper;
    }
    @Override
    public void addVehiculo(VehiculoDTO vehiculoDTO) {
        if (vehiculoDTO.id() != null) {
            Optional<Vehiculo> vehiculoRepositoryById = vehiculoRepository.findById(vehiculoDTO.id());
            if (vehiculoRepositoryById.isPresent()) {
                throw new RuntimeException("Vehiculo already exists");
            }
            Vehiculo vehiculo = vehiculoMapper.dtoToEntity(vehiculoDTO);
            vehiculoRepository.save(vehiculo);
        }
    }
    @Override
    public void updateVehiculo(VehiculoDTO vehiculoDTO) {
        Optional<Vehiculo> vehiculoRepositoryById = vehiculoRepository.findById(vehiculoDTO.id());
        if (vehiculoRepositoryById.isEmpty()) {
            throw new RuntimeException("Vehiculo not found");
        }
        Vehiculo vehiculo = vehiculoMapper.dtoToEntity(vehiculoDTO);
        vehiculoRepository.save(vehiculo);
    }

    @Override
    public void deleteVehiculo(Long id) {
        Optional<Vehiculo> vehiculoRepositoryById = vehiculoRepository.findById(id);
        if (vehiculoRepositoryById.isEmpty()) {
            throw new RuntimeException("Vehiculo not found");
        }
        vehiculoRepository.delete(vehiculoRepositoryById.get());
    }

    @Override
    public Vehiculo findById(Long id) {
        Optional<Vehiculo> vehiculoRepositoryById = vehiculoRepository.findById(id);
        if (vehiculoRepositoryById.isEmpty()) {
            throw new RuntimeException("Vehiculo not found");
        }
        return vehiculoRepositoryById.get();
    }

    @Override
    public List<Vehiculo> findAll() {
        List<Vehiculo> vehiculoList = vehiculoRepository.findAll();
        if (vehiculoList.isEmpty()){
            throw new RuntimeException("Vehiculos not found");
        }
        return vehiculoList;
    }
}
