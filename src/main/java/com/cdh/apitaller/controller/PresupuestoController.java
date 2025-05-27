package com.cdh.apitaller.controller;

import com.cdh.apitaller.dtos.CrearPresupuestoRequestDTO;
import com.cdh.apitaller.dtos.PresupuestoDTO;
import com.cdh.apitaller.entitys.Presupuesto;
import com.cdh.apitaller.services.PresupuestoService;
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
@Tag(name = "PresupuestoController", description = "Controlador para la entidad Presupuesto")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@Slf4j
public class PresupuestoController {
    private final PresupuestoService presupuestoService;

    public PresupuestoController(PresupuestoService presupuestoService) {
        this.presupuestoService = presupuestoService;
    }

    @Operation
            (summary = "Obtener todos los presupuestos",
                    operationId = "getAllPresupuestos",
                    description = "Este endpoint devuelve una lista de todos los presupuestos", tags = {"PresupuestoController"})
    @ApiResponse(responseCode = "200", description = "Lista de presupuestos obtenida correctamente")
    @GetMapping(value = "/presupuesto/GetAllPresupuestos")
    public ResponseEntity<List<Presupuesto>> getAll() throws ParseException {
        log.info("Obteniendo todos los presupuestos");
        List<Presupuesto> presupuestos = presupuestoService.findAll();
        return ResponseEntity.ok(presupuestos);
    }

    @Operation(summary = "Obtener presupuesto por ID",
            operationId = "getPresupuestoById",
            description = "Este endpoint devuelve un presupuesto por su ID", tags = {"PresupuestoController"})
    @ApiResponse(responseCode = "200", description = "Presupuesto obtenido correctamente")
    @GetMapping(value = "/presupuesto/GetPresupuestoById/{id}")
    public ResponseEntity<Presupuesto> getById(@PathVariable Long id) throws ParseException {
        log.info("Obteniendo presupuesto con ID: {}", id);
        Presupuesto presupuesto = presupuestoService.findById(id);
        return ResponseEntity.ok(presupuesto);
    }

    @Operation(summary = "Crear un nuevo presupuesto",
            operationId = "createPresupuesto",
            description = "Este endpoint crea un nuevo presupuesto", tags = {"PresupuestoController"})
    @ApiResponse(responseCode = "201", description = "Presupuesto creado correctamente")
    @PostMapping(value = "/presupuesto/CreatePresupuesto")
    public ResponseEntity<CrearPresupuestoRequestDTO> post(@RequestBody CrearPresupuestoRequestDTO request) throws ParseException {
        log.info("Creando un nuevo presupuesto");
        System.out.println("DTO recibido: " + request.reparacionDTO());
        System.out.println("Matricula: " + request.matricula());
        System.out.println("descripcion: " + request.descripcion());
        presupuestoService.add(request.reparacionDTO(), request.matricula(),request.descripcion());
        return ResponseEntity.ok(request);
    }

    @Operation(summary = "Actualizar un presupuesto",
            operationId = "updatePresupuesto",
            description = "Este endpoint actualiza un presupuesto existente", tags = {"PresupuestoController"})
    @ApiResponse(responseCode = "200", description = "Presupuesto actualizado correctamente")
    @PutMapping(value = "/presupuesto/UpdatePresupuesto")
    public ResponseEntity<PresupuestoDTO> put(@RequestBody PresupuestoDTO presupuestoDTO) throws ParseException {
        log.info("Actualizando presupuesto con ID: {}", presupuestoDTO.id());
        presupuestoService.update(presupuestoDTO);
        return ResponseEntity.ok(presupuestoDTO);
    }

    @Operation(summary = "Eliminar un presupuesto",
            operationId = "deletePresupuesto",
            description = "Este endpoint elimina un presupuesto por su ID", tags = {"PresupuestoController"})
    @ApiResponse(responseCode = "200", description = "Presupuesto eliminado correctamente")
    @DeleteMapping(value = "/presupuesto/DeletePresupuesto/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ParseException {
        log.info("Eliminando presupuesto con ID: {}", id);
        presupuestoService.delete(id);
        return ResponseEntity.ok("Presupuesto eliminado correctamente");
    }
}
