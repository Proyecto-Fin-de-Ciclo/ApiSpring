package com.cdh.apitaller.services.impl;

import com.cdh.apitaller.dtos.OrdenDeTrabajoDTO;
import com.cdh.apitaller.entitys.OrdenDeTrabajo;
import com.cdh.apitaller.mappers.OrdenDeTrabajoMapper;
import com.cdh.apitaller.repository.OrdenDeTrabajoRepository;
import com.cdh.apitaller.services.OrdenDeTrabajoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrdenDeTrabajoServiceImpl implements OrdenDeTrabajoService {
    private final OrdenDeTrabajoMapper ordenDeTrabajoMapper;
    private final OrdenDeTrabajoRepository ordenDeTrabajoRepository;

    public OrdenDeTrabajoServiceImpl(OrdenDeTrabajoMapper ordenDeTrabajoMapper, OrdenDeTrabajoRepository ordenDeTrabajoRepository) {
        this.ordenDeTrabajoMapper = ordenDeTrabajoMapper;
        this.ordenDeTrabajoRepository = ordenDeTrabajoRepository;
    }
    @Override
    public void add(OrdenDeTrabajoDTO ordenDeTrabajoDTO) {
        Optional<OrdenDeTrabajo> byId = ordenDeTrabajoRepository.findById(ordenDeTrabajoDTO.id());
        if (byId.isPresent()) {
            throw new IllegalArgumentException("Orden de trabajo ya existe con el ID: " + ordenDeTrabajoDTO.id());
        }
        OrdenDeTrabajo ordenDeTrabajo = ordenDeTrabajoMapper.dtoToEntity(ordenDeTrabajoDTO);
        ordenDeTrabajoRepository.save(ordenDeTrabajo);
    }

    @Override
    public void update(OrdenDeTrabajoDTO ordenDeTrabajoDTO) {
        Optional<OrdenDeTrabajo> byId = ordenDeTrabajoRepository.findById(ordenDeTrabajoDTO.id());
        if (byId.isEmpty()) {
            throw new IllegalArgumentException("Orden de trabajo no existe con el ID: " + ordenDeTrabajoDTO.id());
        }
        OrdenDeTrabajo ordenDeTrabajo = ordenDeTrabajoMapper.dtoToEntity(ordenDeTrabajoDTO);
        ordenDeTrabajoRepository.save(ordenDeTrabajo);

    }

    @Override
    public void delete(Long id) {
        Optional<OrdenDeTrabajo> byId = ordenDeTrabajoRepository.findById(id);
        if (byId.isEmpty()) {
            throw new IllegalArgumentException("Orden de trabajo no existe con el ID: " + id);
        }
        ordenDeTrabajoRepository.delete(byId.get());

    }

    @Override
    public OrdenDeTrabajo findById(Long id) {
    Optional<OrdenDeTrabajo> byId = ordenDeTrabajoRepository.findById(id);
    if (byId.isEmpty()) {
        throw new IllegalArgumentException("Orden de trabajo no existe con el ID: " + id);
    }
        return byId.get();
    }

    @Override
    public List<OrdenDeTrabajo> findAll() {
    List<OrdenDeTrabajo> ordenes = ordenDeTrabajoRepository.findAll();
    if (ordenes.isEmpty()) {
       throw new  IllegalArgumentException("No existen ordenes de trabajo registradas.");
    }
        return ordenes;
    }
}
