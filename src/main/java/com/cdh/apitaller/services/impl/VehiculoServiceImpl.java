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
        Vehiculo vehiculo = vehiculoMapper.dtoToEntity(vehiculoDTO);

        // Verificamos por matrícula, si usas matrícula como clave única
        Optional<Vehiculo> existing = vehiculoRepository.findByMatricula(vehiculo.getMatricula());
        if (existing.isPresent()) {
            throw new RuntimeException("Vehiculo con matrícula ya existe");
        }

        vehiculoRepository.save(vehiculo);
    }
    @Override
    public void updateVehiculo(VehiculoDTO vehiculoDTO) {
        Optional<Vehiculo> vehiculoRepositoryById = vehiculoRepository.findById(vehiculoDTO.id());
        if (vehiculoRepositoryById.isEmpty()) {
            throw new RuntimeException("Vehiculo not found");
        }
        Vehiculo vehiculoExistente = vehiculoRepositoryById.get();
        vehiculoExistente.setMarca(vehiculoDTO.marca());
        vehiculoExistente.setModelo(vehiculoDTO.modelo());
        vehiculoExistente.setMatricula(vehiculoDTO.matricula());
        vehiculoExistente.setColor(vehiculoDTO.color());
        vehiculoExistente.setNumeroBastidor(vehiculoDTO.numeroBastidor());


        vehiculoRepository.save(vehiculoExistente);
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

    @Override
    public Vehiculo findByMatricula(String matricula) {
    Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findByMatricula(matricula);
    if (vehiculoOptional.isEmpty()) {
        throw new RuntimeException("Vehiculo not found with matricula: " + matricula);
    }
        return vehiculoOptional.get();
    }
}
