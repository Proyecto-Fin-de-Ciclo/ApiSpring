package com.cdh.apitaller.controller;

import com.cdh.apitaller.dtos.TrabajadorDTO;
import com.cdh.apitaller.entitys.Trabajador;
import com.cdh.apitaller.services.TrabajadorService;
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
@Tag(name = "Trabajador", description = "Controlador de Trabajador")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@Slf4j
public class TrabajadorRestController implements GenericController<Trabajador, TrabajadorDTO> {
    private final TrabajadorService trabajadorService;
    public TrabajadorRestController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @Operation(summary = "Obtener todos los trabajadores",
            operationId = "getAllTrabajadores",
            description = "Obtiene una lista de todos los trabajadores")
    @ApiResponse(responseCode = "200", description = "Lista de trabajadores obtenida correctamente")
    @GetMapping(value = "/trabajador/GetAllTrabajadores")
    @Override
    public ResponseEntity<List<Trabajador>> getAll() throws ParseException {
        log.info("Obteniendo todos los trabajadores");
        List<Trabajador> trabajadores = trabajadorService.findAll();
        return ResponseEntity.ok(trabajadores);
    }

    @Operation(summary = "Obtener trabajador por ID",
            operationId = "getTrabajadorById",
            description = "Obtiene un trabajador por su ID")
    @ApiResponse(responseCode = "200", description = "Trabajador obtenido correctamente")
    @GetMapping(value = "/trabajador/GetTrabajadorById/{id}")
    @Override
    public ResponseEntity<Trabajador> getById(@PathVariable Long id) throws ParseException {
        log.info("Obteniendo trabajador con ID: {}", id);
        Trabajador trabajador = trabajadorService.findById(id);
        return ResponseEntity.ok(trabajador);
    }
    @Operation(summary = "Crear un nuevo trabajador",
            operationId = "createTrabajador",
            description = "Crea un nuevo trabajador")
    @ApiResponse(responseCode = "200", description = "Trabajador creado correctamente")
    @PostMapping(value = "/trabajador/CreateTrabajador")
    @Override
    public ResponseEntity<String> post(@RequestBody TrabajadorDTO trabajadorDTO) throws ParseException {
        log.info("Creando nuevo trabajador");
        trabajadorService.add(trabajadorDTO);
        return ResponseEntity.ok("Trabajador creado correctamente");
    }
    @Operation(summary = "Actualizar un trabajador",
            operationId = "updateTrabajador",
            description = "Actualiza un trabajador existente")
    @ApiResponse(responseCode = "200", description = "Trabajador actualizado correctamente")
    @PutMapping(value = "/trabajador/UpdateTrabajador")
    @Override
    public ResponseEntity<String> put(@RequestBody TrabajadorDTO S) throws ParseException {
    log.info("Actualizando trabajador");
        trabajadorService.update(S);
        return ResponseEntity.ok("Trabajador actualizado correctamente");
    }
    @Operation(summary = "Eliminar un trabajador",
            operationId = "deleteTrabajador",
            description = "Elimina un trabajador por su ID")
    @ApiResponse(responseCode = "200", description = "Trabajador eliminado correctamente")
    @DeleteMapping(value = "/trabajador/DeleteTrabajador/{id}")
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) throws ParseException {
        log.info("Eliminando trabajador con ID: {}", id);
        trabajadorService.delete(id);
        return ResponseEntity.ok("Trabajador eliminado correctamente");
    }
}
