package com.cdh.apitaller.controller;

import com.cdh.apitaller.dtos.PiezaDTO;
import com.cdh.apitaller.entitys.Pieza;
import com.cdh.apitaller.services.PiezaService;
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
@Tag(name = "PiezaRestController", description = "Controlador para la entidad Pieza")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@Slf4j
public class PiezaRestController implements GenericController<Pieza, PiezaDTO>{
    private final PiezaService piezaService;
    public PiezaRestController(PiezaService piezaService) {
        this.piezaService = piezaService;
    }
    @Operation(summary = "Obtener todas las piezas",
            operationId = "getAllPiezas",
            description = "Este endpoint devuelve una lista de todas las piezas", tags = {"PiezaRestController"})
    @ApiResponse(responseCode = "200", description = "Lista de piezas obtenida correctamente")
    @GetMapping(value = "/pieza/GetAllPiezas")
    @Override
    public ResponseEntity<List<Pieza>> getAll() throws ParseException {
        log.info("Obteniendo todas las piezas");
        List<Pieza> piezas = piezaService.findAll();
        return ResponseEntity.ok(piezas);
    }

    @Operation(summary = "Obtener pieza por ID",
            operationId = "getPiezaById",
            description = "Este endpoint devuelve una pieza por su ID", tags = {"PiezaRestController"})
    @ApiResponse(responseCode = "200", description = "Pieza obtenida correctamente")
    @GetMapping(value = "/pieza/GetPiezaById/{id}")
    @Override
    public ResponseEntity<Pieza> getById(@PathVariable Long id) throws ParseException {
        log.info("Obteniendo pieza con ID: {}", id);
        Pieza piezaServiceById = piezaService.findById(id);
        return ResponseEntity.ok(piezaServiceById);
    }
    @Operation(summary = "Crear una nueva pieza",
            operationId = "createPieza",
            description = "Este endpoint crea una nueva pieza", tags = {"PiezaRestController"})
    @ApiResponse(responseCode = "200", description = "Pieza creada correctamente")
    @PostMapping(value = "/pieza/CreatePieza")
    @Override
    public ResponseEntity<String> post(@RequestBody PiezaDTO piezaDTO) throws ParseException {
    log.info("Creando nueva pieza");
        piezaService.add(piezaDTO);
        return ResponseEntity.ok("Pieza creada correctamente");
    }
    @Operation(summary = "Actualizar una pieza",
            operationId = "updatePieza",
            description = "Este endpoint actualiza una pieza existente", tags = {"PiezaRestController"})
    @ApiResponse(responseCode = "200", description = "Pieza actualizada correctamente")
    @PutMapping(value = "/pieza/UpdatePieza")
    @Override
    public ResponseEntity<String> put(@RequestBody PiezaDTO S) throws ParseException {
        log.info("Actualizando pieza");
        piezaService.update(S);
        return ResponseEntity.ok("Pieza actualizada correctamente");
    }
    @Operation(summary = "Eliminar una pieza",
            operationId = "deletePieza",
            description = "Este endpoint elimina una pieza por su ID", tags = {"PiezaRestController"})
    @ApiResponse(responseCode = "200", description = "Pieza eliminada correctamente")
    @DeleteMapping(value = "/pieza/DeletePieza/{id}")
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) throws ParseException {
    log.info("Eliminando pieza con ID: {}", id);
        piezaService.delete(id);
        return ResponseEntity.ok("Pieza eliminada correctamente");
    }
}
