package com.cdh.apitaller.services.impl;

import com.cdh.apitaller.dtos.OrdenDeTrabajoDTO;
import com.cdh.apitaller.dtos.ReparacionDTO;
import com.cdh.apitaller.entitys.OrdenDeTrabajo;
import com.cdh.apitaller.entitys.Reparacion;
import com.cdh.apitaller.entitys.Trabajador;
import com.cdh.apitaller.entitys.User;
import com.cdh.apitaller.enums.EstadoReparacion;
import com.cdh.apitaller.mappers.OrdenDeTrabajoMapper;
import com.cdh.apitaller.mappers.ReparacionMapper;
import com.cdh.apitaller.mappers.TrabajadorMapper;
import com.cdh.apitaller.mappers.UserMapper;
import com.cdh.apitaller.repository.OrdenDeTrabajoRepository;
import com.cdh.apitaller.repository.ReparacionRepository;
import com.cdh.apitaller.repository.TrabajadorRepository;
import com.cdh.apitaller.repository.UserRepository;
import com.cdh.apitaller.services.PresupuestoService;
import com.cdh.apitaller.services.ReparacionService;
import com.cdh.apitaller.services.VehiculoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReparacionServiceImpl implements ReparacionService {
    private final ReparacionRepository reparacionRepository;
    private final ReparacionMapper reparacionMapper;
    private final UserRepository userRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final PresupuestoService presupuestoService;
    private final VehiculoService vehiculoService;
    private final TrabajadorMapper trabajadorMapper;
    private final OrdenDeTrabajoMapper ordenDeTrabajoMapper;
    private final OrdenDeTrabajoRepository ordenDeTrabajoRepository;
    private final UserMapper userMapper;

    public ReparacionServiceImpl(ReparacionRepository reparacionRepository, ReparacionMapper reparacionMapper,
                                  UserRepository userRepository, TrabajadorRepository trabajadorRepository,
                                  PresupuestoService presupuestoService, VehiculoService vehiculoService,
                                  TrabajadorMapper trabajadorMapper, OrdenDeTrabajoMapper ordenDeTrabajoMapper,
                                  OrdenDeTrabajoRepository ordenDeTrabajoRepository, UserMapper userMapper) {
        this.reparacionRepository = reparacionRepository;
        this.reparacionMapper = reparacionMapper;
        this.userRepository = userRepository;
        this.trabajadorRepository = trabajadorRepository;
        this.presupuestoService = presupuestoService;
        this.vehiculoService = vehiculoService;
        this.trabajadorMapper = trabajadorMapper;
        this.ordenDeTrabajoMapper = ordenDeTrabajoMapper;
        this.ordenDeTrabajoRepository = ordenDeTrabajoRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void add(ReparacionDTO reparacionDTO) {
        if (reparacionDTO.id() != null) {
            Optional<Reparacion> byId = reparacionRepository.findById(reparacionDTO.id());
            if (byId.isPresent()) {
                throw new RuntimeException("Reparacion with id " + reparacionDTO.id() + " already exists");
            }
        }
        System.out.println("Reparacion: " + reparacionDTO);
        Reparacion reparacion = dtoToEntity(reparacionDTO);
        System.out.println("Reparacion despues del mapper: " + reparacion);
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

    @Override
    public List<Reparacion> findByUserId(Long id) {
        // Comprobar si existe el cliente
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Cliente con ID " + id + " no existe.");
        }
        // Buscar reparaciones del cliente
        List<Reparacion> reparaciones = reparacionRepository.findByUserId(id);
        if (reparaciones.isEmpty()) {
            throw new RuntimeException("No se encontraron reparaciones para el cliente con ID " + id);
        }
        return reparaciones;
    }
    @Override
    public List<Reparacion> findByFecha(LocalDateTime inicio, LocalDateTime fin) {
        return reparacionRepository.findByHoraInicioBetween(inicio, fin);
    }

    @Override
    public Reparacion updateEstado(Long id, String estado) {
    Optional<Reparacion> reparacionOpt = reparacionRepository.findById(id);
    if(reparacionOpt.isEmpty()){
        throw new RuntimeException("Reparacion with id " + id + " does not exist");
    }
        Reparacion reparacion = reparacionOpt.get();
            reparacion.setEstado(EstadoReparacion.valueOf(estado));
            reparacionRepository.save(reparacion);
            return reparacion;
    }

    @Override
    public OrdenDeTrabajoDTO addOrdenDeTrabajo(ReparacionDTO reparacionDTO, String descripcion,String matricula) {
        OrdenDeTrabajo ordenDeTrabajo = new OrdenDeTrabajo();
        ordenDeTrabajo.setDescripcionTrabajo(descripcion);
        ordenDeTrabajo.setEstadoOrdenDeTrabajo(reparacionDTO.estado());
        ordenDeTrabajo.setVehiculo(vehiculoService.findByMatricula(matricula));
        Trabajador trabajador = trabajadorMapper.dtoToEntity(reparacionDTO.trabajador());
        ordenDeTrabajo.setTrabajadores(List.of(trabajador));
        ordenDeTrabajo.setPiezas(reparacionDTO.piezas());
        User user = userMapper.dtoToEntity(reparacionDTO.user());
        ordenDeTrabajo.setUser(user);
        ordenDeTrabajoRepository.save(ordenDeTrabajo);
        return ordenDeTrabajoMapper.entityToDto(ordenDeTrabajo);
    }

    public Reparacion dtoToEntity(ReparacionDTO reparacionDTO) {
        Reparacion reparacion = new Reparacion();

        reparacion.setDescripcion(reparacionDTO.descripcion());
        reparacion.setHoraInicio(reparacionDTO.horaInicio());
        reparacion.setHoraFin(reparacionDTO.horaFin());
        reparacion.setEstado(reparacionDTO.estado());

        // Mapear Trabajador si existe y buscarlo en base de datos por códigoEmpleado
        if (reparacionDTO.trabajador() != null && reparacionDTO.trabajador().codigoEmpleado() != null) {
            Trabajador trabajador = trabajadorRepository.findByCodigoEmpleado(reparacionDTO.trabajador().codigoEmpleado());
            reparacion.setTrabajador(trabajador);
        }

        // Mapear User si existe y buscarlo en base de datos por nombreUsuarioApp
        if (reparacionDTO.user() != null && reparacionDTO.user().nombreUsuarioApp() != null) {
            User user = userRepository.findByNombreUsuarioApp(reparacionDTO.user().nombreUsuarioApp());
            reparacion.setUser(user);
        }

        return reparacion;
    }

    @Override
    public Reparacion updateFinalTime(Reparacion reparacion) {
    Optional<Reparacion> reparacionOpt = reparacionRepository.findById(reparacion.getId());
    if (reparacionOpt.isEmpty()) {
        throw new RuntimeException("Reparacion with id " + reparacion.getId() + " does not exist");
    }
        Reparacion existingReparacion = reparacionOpt.get();
        existingReparacion.setHoraFin(LocalDateTime.now());
        reparacionRepository.save(existingReparacion);
        return existingReparacion;

    }
    @Override
    public void updateFechaFinalReparacion(Long id, LocalDateTime fechaFinalReparacion) {
        List<Reparacion> byUserId = reparacionRepository.findByUserId(id);
        if (byUserId.isEmpty()) {
            throw new IllegalArgumentException("No existen reparaciones para el usuario con ID: " + id);
        }
        Optional<Reparacion> reparacionSinFechaFin =byUserId.stream()
                .filter(r -> r.getHoraFin() == null)
                .findFirst()
                .map(r -> {r.setHoraFin(fechaFinalReparacion);
                    return r;});
        reparacionRepository.save(reparacionSinFechaFin.orElseThrow(() -> new IllegalArgumentException("No se encontró una reparación sin fecha de fin para el usuario con ID: " + id)));
    }

    @Override
    public Reparacion obtenerReparacionActivaConPresupuestoAceptado(Long userId, Long vehiculoId) {
        Optional<Reparacion> reparacionActiva = reparacionRepository.findReparacionActiva(userId, vehiculoId);

        if (reparacionActiva.isEmpty()) {
            throw new RuntimeException("No hay reparaciones activas para el usuario con ID: " + userId + " y vehículo con ID: " + vehiculoId);
        }

        boolean hayPresupuestoAceptado = presupuestoService.existsByUserIdAndVehiculoIdAndAceptadoTrue(userId, vehiculoId);

        if (!hayPresupuestoAceptado) {
            throw new RuntimeException("No hay presupuesto aceptado para el usuario con ID: " + userId + " y vehículo con ID: " + vehiculoId);
        }

        return reparacionActiva.get();
    }
}





