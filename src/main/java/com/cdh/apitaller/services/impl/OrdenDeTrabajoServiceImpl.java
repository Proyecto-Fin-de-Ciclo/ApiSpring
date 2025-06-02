package com.cdh.apitaller.services.impl;

import com.cdh.apitaller.dtos.OrdenDeTrabajoDTO;
import com.cdh.apitaller.entitys.OrdenDeTrabajo;
import com.cdh.apitaller.entitys.Reparacion;
import com.cdh.apitaller.entitys.Trabajador;
import com.cdh.apitaller.mappers.OrdenDeTrabajoMapper;
import com.cdh.apitaller.repository.OrdenDeTrabajoRepository;
import com.cdh.apitaller.repository.ReparacionRepository;
import com.cdh.apitaller.services.OrdenDeTrabajoService;
import com.cdh.apitaller.services.ReparacionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class OrdenDeTrabajoServiceImpl implements OrdenDeTrabajoService {
    private final OrdenDeTrabajoMapper ordenDeTrabajoMapper;
    private final OrdenDeTrabajoRepository ordenDeTrabajoRepository;
    private final ReparacionService reparacionService;

    public OrdenDeTrabajoServiceImpl(OrdenDeTrabajoMapper ordenDeTrabajoMapper, OrdenDeTrabajoRepository ordenDeTrabajoRepository, ReparacionService reparacionService) {
        this.ordenDeTrabajoMapper = ordenDeTrabajoMapper;
        this.ordenDeTrabajoRepository = ordenDeTrabajoRepository;
        this.reparacionService = reparacionService;
    }

    @Override
    public void add(OrdenDeTrabajoDTO ordenDeTrabajoDTO) {
        Optional<OrdenDeTrabajo> byId = ordenDeTrabajoRepository.findById(ordenDeTrabajoDTO.id());
        if (byId.isPresent()) {
            throw new IllegalArgumentException("Orden de trabajo ya existe con el ID: " + ordenDeTrabajoDTO.id());
        }
        List<Reparacion> reparacionesUsuario = reparacionService.findByUserId(ordenDeTrabajoDTO.userDTO().id());

        boolean tieneReparacionAbierta = reparacionesUsuario.stream()
                .anyMatch(reparacion -> reparacion.getHoraFin() == null);

        if (!tieneReparacionAbierta) {
            throw new IllegalStateException("Todas las reparaciones del cliente están finalizadas. Debe crear una nueva reparación antes de generar la orden de trabajo.");
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

    @Override
    public void updateFechaFinalReparacion(Long id, LocalDateTime fechaFinalReparacion,OrdenDeTrabajoDTO ordenDeTrabajoDTO) {
        reparacionService.updateFechaFinalReparacion(id, fechaFinalReparacion);
        OrdenDeTrabajo ordenDeTrabajo = ordenDeTrabajoMapper.dtoToEntity(ordenDeTrabajoDTO);
        ordenDeTrabajoRepository.delete(ordenDeTrabajo);

    }

    @Override
    public List<OrdenDeTrabajo> findByTrabajadorId(Long id) {
        return ordenDeTrabajoRepository.findByTrabajadorId(id);
    }
}
