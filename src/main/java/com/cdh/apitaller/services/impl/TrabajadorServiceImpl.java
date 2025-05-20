package com.cdh.apitaller.services.impl;

import com.cdh.apitaller.dtos.TrabajadorDTO;
import com.cdh.apitaller.entitys.Trabajador;
import com.cdh.apitaller.mappers.TrabajadorMapper;
import com.cdh.apitaller.repository.TrabajadorRepository;
import com.cdh.apitaller.services.TrabajadorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {
    private final TrabajadorRepository trabajadorRepository;
    private final TrabajadorMapper trabajadorMapper;

    public TrabajadorServiceImpl(TrabajadorRepository trabajadorRepository, TrabajadorMapper trabajadorMapper) {
        this.trabajadorRepository = trabajadorRepository;
        this.trabajadorMapper = trabajadorMapper;
    }

    @Override
    public void add(TrabajadorDTO trabajadorDTO) {
        Optional<Trabajador> trabajadorRepositoryById = trabajadorRepository.findById(trabajadorDTO.id());
        if (trabajadorRepositoryById.isPresent()) {
            throw new RuntimeException("Trabajador with id " + trabajadorDTO.id() + " already exists");
        }
        Trabajador trabajador = trabajadorMapper.dtoToEntity(trabajadorDTO);
        trabajadorRepository.save(trabajador);
    }

    @Override
    public void update(TrabajadorDTO trabajadorDTO) {
        Optional<Trabajador> trabajadorRepositoryById = trabajadorRepository.findById(trabajadorDTO.id());
        if (trabajadorRepositoryById.isEmpty()) {
            throw new RuntimeException("Trabajador with id " + trabajadorDTO.id() + " does not exist");
        }
        Trabajador trabajador = trabajadorMapper.dtoToEntity(trabajadorDTO);
        trabajadorRepository.save(trabajador);
    }

    @Override
    public void delete(TrabajadorDTO trabajadorDTO) {
        Optional<Trabajador> trabajadorRepositoryById = trabajadorRepository.findById(trabajadorDTO.id());
        if (trabajadorRepositoryById.isEmpty()) {
            throw new RuntimeException("Trabajador with id " + trabajadorDTO.id() + " does not exist");
        }
        Trabajador trabajador = trabajadorMapper.dtoToEntity(trabajadorDTO);
        trabajadorRepository.delete(trabajador);
    }

    @Override
    public Trabajador findById(Long id) {
        Optional<Trabajador> trabajadorRepositoryById = trabajadorRepository.findById(id);
        if (trabajadorRepositoryById.isEmpty()) {
            throw new RuntimeException("Trabajador with id " + id + " does not exist");
        }
        Trabajador trabajador= trabajadorRepositoryById.get();
        return trabajador;
    }

    @Override
    public List<Trabajador> findAll() {
        List<Trabajador> trabajadorList = trabajadorRepository.findAll();
        if (trabajadorList.isEmpty()) {
            throw new RuntimeException("No hay trabajadores");
        }
        return trabajadorList;
    }
}
