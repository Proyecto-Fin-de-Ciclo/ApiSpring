package com.cdh.apitaller.services.impl;

import com.cdh.apitaller.dtos.PresupuestoDTO;
import com.cdh.apitaller.dtos.ReparacionDTO;
import com.cdh.apitaller.entitys.Pieza;
import com.cdh.apitaller.entitys.Presupuesto;
import com.cdh.apitaller.entitys.User;
import com.cdh.apitaller.entitys.Vehiculo;
import com.cdh.apitaller.mappers.PresupuestoMapper;
import com.cdh.apitaller.mappers.UserMapper;
import com.cdh.apitaller.repository.PresupuestoRepository;
import com.cdh.apitaller.repository.VehiculoRepository;
import com.cdh.apitaller.services.PresupuestoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class PresupuestoServiceImpl implements PresupuestoService {
    private final PresupuestoRepository presupuestoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final UserMapper userMapper;
    private final PresupuestoMapper presupuestoMapper;


    @Value("${taller.nombre}")
    private String nombreTaller;
    @Value("${taller.direccion}")
    private String direccionTaller;
    @Value("${taller.telefono}")
    private String telefonoTaller;

    public PresupuestoServiceImpl(PresupuestoRepository presupuestoRepository, VehiculoRepository vehiculoRepository, UserMapper userMapper, PresupuestoMapper presupuestoMapper) {
        this.presupuestoRepository = presupuestoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.userMapper = userMapper;
        this.presupuestoMapper = presupuestoMapper;
    }

    @Override
    public void add(ReparacionDTO reparacionDTO, String matricula, String descripcion) {
        System.out.println("matricula: " + matricula);
        Vehiculo vehiculo = vehiculoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        // ⚠️ Validar que no exista un presupuesto no aceptado para este vehículo
        Optional<Presupuesto> presupuestoNoAceptado = presupuestoRepository.findByVehiculoAndAceptadoFalse(vehiculo);
        if (presupuestoNoAceptado.isPresent()) {
            throw new RuntimeException("Ya existe un presupuesto pendiente para este vehículo. No se puede crear otro.");
        }

        // Crear nuevo presupuesto si no hay uno pendiente
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setNombreTaller(nombreTaller);
        presupuesto.setDireccionTaller(direccionTaller);
        presupuesto.setTelefonoTaller(telefonoTaller);
        presupuesto.setDescripcionTrabajo(descripcion);
        User user = userMapper.dtoToEntity(reparacionDTO.user());
        presupuesto.setPiezas(reparacionDTO.piezas());

        double subtotal = reparacionDTO.piezas().stream()
                .mapToDouble(Pieza::getPrecio)
                .sum();
        presupuesto.setSubtotalPiezas(subtotal);
        presupuesto.setTotalConIVA(subtotal * 1.21);
        presupuesto.setVehiculo(vehiculo);
        presupuesto.setAceptado(false);

        presupuestoRepository.save(presupuesto);
    }


    @Override
    public void update(PresupuestoDTO presupuestoDTO) {
        Optional<Presupuesto> presupuestoRepositoryById = presupuestoRepository.findById(presupuestoDTO.id());
        if (presupuestoRepositoryById.isEmpty()) {
            throw new RuntimeException("Presupuesto no encontrado");
        }
        Presupuesto presupuesto = presupuestoMapper.dtoToEntity(presupuestoDTO);
        presupuestoRepository.save(presupuesto);
    }

    @Override
    public void delete(Long id) {

        presupuestoRepository.deleteById(id);

    }

    @Override
    public Presupuesto findById(Long id) {
        Optional<Presupuesto> presupuestoRepositoryById = presupuestoRepository.findById(id);
        if( presupuestoRepositoryById.isEmpty()){
            throw new RuntimeException("Presupuesto no encontrado");
        }
        return presupuestoRepositoryById.get();
    }

    @Override
    public List<Presupuesto> findAll() {
    List<Presupuesto> presupuestos = presupuestoRepository.findAll();
    if (presupuestos.isEmpty()) {
            throw new RuntimeException("No hay presupuestos disponibles");
        }
        return presupuestos;
    }

    @Override
    public boolean existsByUserIdAndVehiculoIdAndAceptadoTrue(Long userId, Long vehiculoId) {
        return presupuestoRepository.existsByVehiculoPropietarioIdAndVehiculoIdAndAceptadoTrue(userId, vehiculoId);
    }

    @Override
    public List<Presupuesto> findAllByAceptadoTrue() {
        return presupuestoRepository.findAllByAceptadoTrue();
    }
}
