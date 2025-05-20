package com.cdh.apitaller.services.impl;

import com.cdh.apitaller.dtos.CitaDTO;
import com.cdh.apitaller.entitys.Cita;
import com.cdh.apitaller.mappers.CitaMapper;
import com.cdh.apitaller.repository.CitaRepository;
import com.cdh.apitaller.services.CitaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {
private final CitaRepository citaRepository;
private final CitaMapper citaMapper;

    public CitaServiceImpl(CitaRepository citaRepository, CitaMapper citaMapper) {
        this.citaRepository = citaRepository;
        this.citaMapper = citaMapper;
    }

    @Override
    public void add(CitaDTO citaDTO) {
        Optional<Cita> byId = citaRepository.findById(citaDTO.id());
        if (byId.isPresent()) {
            throw new RuntimeException("Cita with id " + citaDTO.id() + " already exists");
        }
        Cita cita = citaMapper.dtoToEntity(citaDTO);
        citaRepository.save(cita);
        
    }

    @Override
    public void update(CitaDTO citaDTO) {
        Optional<Cita> byId = citaRepository.findById(citaDTO.id());
        if (byId.isEmpty()) {
            throw new RuntimeException("Cita with id " + citaDTO.id() + " does not exist");
        }
        Cita cita = citaMapper.dtoToEntity(citaDTO);
        citaRepository.save(cita);
    }

    @Override
    public void delete(CitaDTO citaDTO) {
        Optional<Cita> byId = citaRepository.findById(citaDTO.id());
        if (byId.isEmpty()) {
            throw new RuntimeException("Cita with id " + citaDTO.id() + " does not exist");
        }
        Cita cita = citaMapper.dtoToEntity(citaDTO);
        citaRepository.delete(cita);
    }

    @Override
    public Cita findById(Long id) {
        Optional<Cita> byId = citaRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("Cita with id " + id + " does not exist");
        }
        return byId.get();
    }

    @Override
    public List<Cita> findAll() {
        return citaRepository.findAll();

    }
}
