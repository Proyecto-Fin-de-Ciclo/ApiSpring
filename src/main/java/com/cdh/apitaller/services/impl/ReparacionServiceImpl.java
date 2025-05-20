package com.cdh.apitaller.services.impl;

import com.cdh.apitaller.dtos.ReparacionDTO;
import com.cdh.apitaller.entitys.Reparacion;
import com.cdh.apitaller.mappers.ReparacionMapper;
import com.cdh.apitaller.repository.ReparacionRepository;
import com.cdh.apitaller.services.ReparacionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReparacionServiceImpl implements ReparacionService {
    private final ReparacionRepository reparacionRepository;
    private final ReparacionMapper reparacionMapper;

    public ReparacionServiceImpl(ReparacionRepository reparacionRepository, ReparacionMapper reparacionMapper) {
        this.reparacionRepository = reparacionRepository;
        this.reparacionMapper = reparacionMapper;
    }

    @Override
    public void add(ReparacionDTO reparacionDTO) {
        if (reparacionDTO.id() != null) {
            Optional<Reparacion> byId = reparacionRepository.findById(reparacionDTO.id());
            if (byId.isPresent()) {
                throw new RuntimeException("Reparacion with id " + reparacionDTO.id() + " already exists");
            }
        }
        Reparacion reparacion = reparacionMapper.dtoToEntity(reparacionDTO);
        reparacionRepository.save(reparacion);
    }

    @Override
    public void update(ReparacionDTO reparacionDTO) {
        Optional<Reparacion> byId = reparacionRepository.findById(reparacionDTO.id());
        if (byId.isEmpty()) {
            throw new RuntimeException("Reparacion with id " + reparacionDTO.id() + " does not exist");
        }
        Reparacion reparacion = reparacionMapper.dtoToEntity(reparacionDTO);
        reparacionRepository.save(reparacion);

    }

    @Override
    public void delete(Long id) {
        Optional<Reparacion> byId = reparacionRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("Reparacion with id " + id + " does not exist");
        }
        reparacionRepository.delete(byId.get());
    }

    @Override
    public Reparacion findById(Long id) {
        Optional<Reparacion> byId = reparacionRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("Reparacion with id " + id + " does not exist");
        }
        Reparacion reparacion = byId.get();
        return reparacion;
    }

    @Override
    public List<Reparacion> findAll() {
        List<Reparacion> reparacionList = reparacionRepository.findAll();
        if (reparacionList.isEmpty()) {
            throw new RuntimeException("No reparaciones found");
        }
        return reparacionList;
    }
}
