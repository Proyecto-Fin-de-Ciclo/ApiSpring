package com.cdh.apitaller.controller;

import com.cdh.apitaller.dtos.OrdenDeTrabajoDTO;
import com.cdh.apitaller.dtos.ReparacionDTO;
import com.cdh.apitaller.entitys.OrdenDeTrabajo;
import com.cdh.apitaller.entitys.Reparacion;
import com.cdh.apitaller.mappers.ReparacionMapper;
import com.cdh.apitaller.services.ReparacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
@RestController
@Tag(name = "ReparacionRestController", description = "Controlador para la entidad Reparacion")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@Slf4j
public class ReparacionRestController {
    private final ReparacionService reparacionService;
    private final OrdenDeTrabajoController ordenDeTrabajoController;
    private final ReparacionMapper reparacionMapper;

    public ReparacionRestController(ReparacionService reparacionService, OrdenDeTrabajoController ordenDeTrabajoController, ReparacionMapper reparacionMapper) {
        this.reparacionService = reparacionService;
        this.ordenDeTrabajoController = ordenDeTrabajoController;
        this.reparacionMapper = reparacionMapper;
    }

    @Operation(summary = "Obtener todas las reparaciones",
            operationId = "getAllReparaciones",
            description = "Este endpoint devuelve una lista de todas las reparaciones", tags = {"ReparacionRestController"})
    @ApiResponse(responseCode = "200", description = "Lista de reparaciones obtenida correctamente")
    @GetMapping(value = "/reparacion/GetAllReparaciones")
    public ResponseEntity<List<Reparacion>> getAll() throws ParseException {
        log.info("Obteniendo todas las reparaciones");
        List<Reparacion> reparaciones = reparacionService.findAll();
        return ResponseEntity.ok(reparaciones);
    }

    @Operation(summary = "Obtener reparacion por ID",
            operationId = "getReparacionById",
            description = "Este endpoint devuelve una reparacion por su ID", tags = {"ReparacionRestController"})
    @ApiResponse(responseCode = "200", description = "Reparacion obtenida correctamente")
    @GetMapping(value = "/reparacion/GetReparacionById/{id}")
    public ResponseEntity<Reparacion> getById(@PathVariable Long id) throws ParseException {
        log.info("Obteniendo reparacion con ID: {}", id);
        Reparacion reparacion = reparacionService.findById(id);
        return ResponseEntity.ok(reparacion);
    }


    @Operation(summary = "Obtener reparaciones por ID de usuario",
            operationId = "getReparacionesByUserId",
            description = "Este endpoint devuelve una lista de reparaciones por ID de usuario", tags = {"ReparacionRestController"})
    @ApiResponse(responseCode = "200", description = "Lista de reparaciones obtenida correctamente")
    @GetMapping(value = "/reparacion/GetReparacionesByUserId/{id}")
    public ResponseEntity<List<Reparacion>> getByUserId(@PathVariable Long id) throws ParseException {
        log.info("Obteniendo reparaciones por ID de usuario: {}", id);
        List<Reparacion> reparaciones = reparacionService.findByUserId(id);
        return ResponseEntity.ok(reparaciones);
    }

    @Operation(summary = "Obtener reparaciones por fecha",
            operationId = "getReparacionesByFecha",
            description = "Este endpoint devuelve una lista de reparaciones por fecha", tags = {"ReparacionRestController"})
    @ApiResponse(responseCode = "200", description = "Lista de reparaciones obtenida correctamente")
    @GetMapping(value = "/reparacion/GetReparacionesByFecha")
    public ResponseEntity<List<Reparacion>> getByFecha(@RequestParam LocalDateTime inicio,
                                                       @RequestParam(required = false) LocalDateTime fin) throws ParseException {
        log.info("Obteniendo reparaciones por fecha");
        List<Reparacion> reparaciones = reparacionService.findByFecha(inicio, fin);
        return ResponseEntity.ok(reparaciones);
    }

    @Operation(summary = "Crear una nueva reparacion",
            operationId = "createReparacion",
            description = "Este endpoint crea una nueva reparacion", tags = {"ReparacionRestController"})
    @ApiResponse(responseCode = "201", description = "Reparacion creada correctamente")
    @PostMapping(value = "/reparacion/CreateReparacion")
    public ResponseEntity<ReparacionDTO> post(@RequestBody ReparacionDTO reparacionDTO) throws ParseException {
        log.info("Creando nueva reparacion");
        System.out.println("DTO recibido: " + reparacionDTO);
        System.out.println("Trabajador: " + reparacionDTO.trabajador());
        System.out.println("User: " + reparacionDTO.user());
        reparacionService.add(reparacionDTO);
        return ResponseEntity.ok(reparacionDTO);
    }

    @Operation(summary = "Actualizar una reparacion",
            operationId = "updateReparacion",
            description = "Este endpoint actualiza una reparacion", tags = {"ReparacionRestController"})
    @ApiResponse(responseCode = "200", description = "Reparacion actualizada correctamente")
    @PutMapping(value = "/reparacion/UpdateReparacion")
    public ResponseEntity<ReparacionDTO> put(@RequestBody ReparacionDTO S) throws ParseException {
        log.info("Actualizando reparacion");
        reparacionService.update(S);
        return ResponseEntity.ok(S);
    }

    @Operation(summary = "Actualizar estado de una reparacion",
            operationId = "updateEstadoReparacion",
            description = "Este endpoint actualiza el estado de una reparacion", tags = {"ReparacionRestController"})
    @ApiResponse(responseCode = "200", description = "Estado de reparacion actualizado correctamente")
    @PutMapping(value = "/reparacion/UpdateEstadoReparacion/{id}/{estado}")
    public ResponseEntity<Reparacion> updateEstado(@PathVariable Long id, @PathVariable String estado) throws ParseException {
        log.info("Actualizando estado de reparacion con ID: {}", id);
        Reparacion reparacion = reparacionService.updateEstado(id, estado);
        return ResponseEntity.ok(reparacion);
    }

    @Operation(summary = "Eliminar una reparacion",
            operationId = "deleteReparacion",
            description = "Este endpoint elimina una reparacion", tags = {"ReparacionRestController"})
    @ApiResponse(responseCode = "200", description = "Reparacion eliminada correctamente")
    @DeleteMapping(value = "/reparacion/DeleteReparacion/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ParseException {
        log.info("Eliminando reparacion con ID: {}", id);
        reparacionService.delete(id);
        return ResponseEntity.ok("Reparacion eliminada correctamente");
    }

    @PostMapping("/reparacion/AddOrdenDeTrabajo")
    @Operation(summary = "Agregar orden de trabajo a una reparacion", operationId = "addOrdenDeTrabajo")
    @ApiResponse(responseCode = "200", description = "Orden de trabajo agregada correctamente")
    public ResponseEntity<OrdenDeTrabajoDTO> addOrdenDeTrabajo(
            @RequestBody ReparacionDTO reparacionDTO,
            @RequestParam String descripcion,
            @RequestParam String matricula) throws ParseException {

        log.info("Agregando orden de trabajo para la reparacion");
        OrdenDeTrabajoDTO ordenDeTrabajoDTO = reparacionService.addOrdenDeTrabajo(reparacionDTO, descripcion, matricula);
        ordenDeTrabajoController.post(ordenDeTrabajoDTO);
        return ResponseEntity.ok(ordenDeTrabajoDTO);
    }

}
