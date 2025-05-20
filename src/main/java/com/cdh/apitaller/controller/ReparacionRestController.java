package com.cdh.apitaller.controller;

import com.cdh.apitaller.dtos.ReparacionDTO;
import com.cdh.apitaller.entitys.Reparacion;
import com.cdh.apitaller.services.ReparacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
public class ReparacionRestController implements GenericController<Reparacion, ReparacionDTO>{
    private final ReparacionService reparacionService;

    public ReparacionRestController(ReparacionService reparacionService) {
        this.reparacionService = reparacionService;
    }
    @Operation(summary = "Obtener todas las reparaciones",
            operationId = "getAllReparaciones",
            description = "Este endpoint devuelve una lista de todas las reparaciones", tags = {"ReparacionRestController"})
    @ApiResponse(responseCode = "200", description = "Lista de reparaciones obtenida correctamente")
    @GetMapping(value = "/reparacion/GetAllReparaciones")
    @Override
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
    @Override
    public ResponseEntity<Reparacion> getById(@PathVariable Long id) throws ParseException {
        log.info("Obteniendo reparacion con ID: {}", id);
        Reparacion reparacion = reparacionService.findById(id);
        return ResponseEntity.ok(reparacion);
    }
    @Operation(summary = "Crear una nueva reparacion",
            operationId = "createReparacion",
            description = "Este endpoint crea una nueva reparacion", tags = {"ReparacionRestController"})
    @ApiResponse(responseCode = "201", description = "Reparacion creada correctamente")
    @PostMapping(value = "/reparacion/CreateReparacion")
    @Override
    public ResponseEntity<String> post(@RequestBody ReparacionDTO reparacionDTO) throws ParseException {
    log.info("Creando nueva reparacion");
        reparacionService.add(reparacionDTO);
        return ResponseEntity.ok("Reparacion creada correctamente");
    }
    @Operation(summary = "Actualizar una reparacion",
            operationId = "updateReparacion",
            description = "Este endpoint actualiza una reparacion", tags = {"ReparacionRestController"})
    @ApiResponse(responseCode = "200", description = "Reparacion actualizada correctamente")
    @PutMapping(value = "/reparacion/UpdateReparacion")
    @Override
    public ResponseEntity<String> put(@RequestBody ReparacionDTO S) throws ParseException {
        log.info("Actualizando reparacion");
        reparacionService.update(S);
        return ResponseEntity.ok("Reparacion actualizada correctamente");
    }
    @Operation(summary = "Eliminar una reparacion",
            operationId = "deleteReparacion",
            description = "Este endpoint elimina una reparacion", tags = {"ReparacionRestController"})
    @ApiResponse(responseCode = "200", description = "Reparacion eliminada correctamente")
    @DeleteMapping(value = "/reparacion/DeleteReparacion/{id}")
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) throws ParseException {
    log.info("Eliminando reparacion con ID: {}", id);
        reparacionService.delete(id);
        return ResponseEntity.ok("Reparacion eliminada correctamente");
    }
}
