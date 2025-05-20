package com.cdh.apitaller.controller;

import com.cdh.apitaller.dtos.CitaDTO;
import com.cdh.apitaller.entitys.Cita;
import com.cdh.apitaller.services.CitaService;
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
@Tag(name = "CitaRestController", description = "Controlador para la entidad Cita")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@Slf4j
public class CitaRestController implements GenericController<Cita,CitaDTO> {
    private final CitaService citaService;

    public CitaRestController(CitaService citaService) {
        this.citaService = citaService;
    }
    @Operation(summary = "Obtener todas las citas",
            operationId = "getAllCitas",
            description = "Este endpoint devuelve una lista de todas las citas", tags = {"CitaRestController"})
    @ApiResponse(responseCode = "200", description = "Lista de citas obtenida correctamente")
    @GetMapping(value = "/cita/GetAllCitas")
    @Override
    public ResponseEntity<List<Cita>> getAll() throws ParseException {
        log.info("Obteniendo todas las citas");
        List<Cita> citas = citaService.findAll();
        return ResponseEntity.ok(citas);
    }

    @Operation(summary = "Obtener cita por ID",
            operationId = "getCitaById",
            description = "Este endpoint devuelve una cita por su ID", tags = {"CitaRestController"})
    @ApiResponse(responseCode = "200", description = "Cita obtenida correctamente")
    @GetMapping(value = "/cita/GetCitaById/{id}")
    @Override
    public ResponseEntity<Cita> getById(@PathVariable Long id) throws ParseException {
        log.info("Obteniendo cita con ID: {}", id);
        Cita cita = citaService.findById(id);
        return ResponseEntity.ok(cita);
    }

    @Operation(summary = "Crear una nueva cita",
            operationId = "createCita",
            description = "Este endpoint crea una nueva cita", tags = {"CitaRestController"})
    @ApiResponse(responseCode = "200", description = "Cita creada correctamente")
    @PostMapping(value = "/cita/CreateCita")
    @Override
    public ResponseEntity<String> post(@RequestBody CitaDTO citaDTO) throws ParseException {
        log.info("Creando nueva cita: {}", citaDTO);
        citaService.add(citaDTO);
        return ResponseEntity.ok("cita creada correctamente");
    }

    @Operation(summary = "Actualizar una cita",
            operationId = "updateCita",
            description = "Este endpoint actualiza una cita existente", tags = {"CitaRestController"})
    @ApiResponse(responseCode = "200", description = "Cita actualizada correctamente")
    @PutMapping(value = "/cita/UpdateCita")
    @Override
    public ResponseEntity<String> put(@RequestBody CitaDTO citaDTO) throws ParseException {
        log.info("Actualizando cita: {}", citaDTO);
        citaService.update(citaDTO);
        return ResponseEntity.ok("cita actualizada correctamente");
    }

    @Operation(summary = "Eliminar una cita",
            operationId = "deleteCita",
            description = "Este endpoint elimina una cita por su ID", tags = {"CitaRestController"})
    @ApiResponse(responseCode = "200", description = "Cita eliminada correctamente")
    @DeleteMapping(value = "/cita/DeleteCita/{id}")
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) throws ParseException {
        log.info("Cita con ID: {} eliminada correctamente", id);
        citaService.delete(id);
        return ResponseEntity.ok("cita eliminada correctamente");
    }
}
