package com.cdh.apitaller.controller;

import com.cdh.apitaller.dtos.CompaniaAseguradoraDTO;
import com.cdh.apitaller.entitys.CompaniaAseguradora;
import com.cdh.apitaller.services.CompaniaAseguradoraService;
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
@Tag(name = "CompaniaAseguradoraRestController", description = "Controlador para la entidad CompaniaAseguradora")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@Slf4j
public class CompaniaAseguradoraRestController{
    private final CompaniaAseguradoraService companiaAseguradoraService;

    public CompaniaAseguradoraRestController(CompaniaAseguradoraService companiaAseguradoraService) {
        this.companiaAseguradoraService = companiaAseguradoraService;
    }
    @Operation(summary = "Obtener todas) las compañias aseguradoras",
            operationId = "getAllCompaniasAseguradoras",
            description = "Este endpoint devuelve una lista de todas las compañias aseguradoras", tags = {"CompaniaAseguradoraRestController"})
    @ApiResponse(responseCode = "200", description = "Lista de compañias aseguradoras obtenida correctamente")
    @GetMapping(value = "/companiaAseguradora/GetAllCompaniasAseguradoras")
    public ResponseEntity<List<CompaniaAseguradora>> getAll() throws ParseException {
        log.info("Obteniendo todas las compañias aseguradoras");
        List<CompaniaAseguradora> companiasAseguradoras = companiaAseguradoraService.findAll();
        return ResponseEntity.ok(companiasAseguradoras);
    }

    @Operation(summary = "Obtener compañia aseguradora por ID",
            operationId = "getCompaniaAseguradoraById",
            description = "Este endpoint devuelve una compañia aseguradora por su ID", tags = {"CompaniaAseguradoraRestController"})
    @ApiResponse(responseCode = "200", description = "Compañia aseguradora obtenida correctamente")
    @GetMapping(value = "/companiaAseguradora/GetCompaniaAseguradoraById/{id}")
    public ResponseEntity<CompaniaAseguradora> getById(@PathVariable Long id) throws ParseException {
    log.info("Obteniendo compañia aseguradora con ID: {}", id);
        CompaniaAseguradora companiaAseguradora = companiaAseguradoraService.findById(id);
        return ResponseEntity.ok(companiaAseguradora);
    }
    @Operation(summary = "Crear una nueva compañia aseguradora",
            operationId = "createCompaniaAseguradora",
            description = "Este endpoint crea una nueva compañia aseguradora", tags = {"CompaniaAseguradoraRestController"})
    @ApiResponse(responseCode = "200", description = "Compañia aseguradora creada correctamente")
    @PostMapping(value = "/companiaAseguradora/CreateCompaniaAseguradora")
    public ResponseEntity<CompaniaAseguradoraDTO> post(@RequestBody CompaniaAseguradoraDTO companiaAseguradoraDTO) throws ParseException {
    log.info("Creando compañia aseguradora");
        companiaAseguradoraService.add(companiaAseguradoraDTO);
        return ResponseEntity.ok(companiaAseguradoraDTO);
    }
    @Operation(summary = "Actualizar compañia aseguradora",
            operationId = "updateCompaniaAseguradora",
            description = "Este endpoint actualiza una compañia aseguradora", tags = {"CompaniaAseguradoraRestController"})
    @ApiResponse(responseCode = "200", description = "Compañia aseguradora actualizada correctamente")
    @PutMapping(value = "/companiaAseguradora/UpdateCompaniaAseguradora/{id}")
    public ResponseEntity<CompaniaAseguradoraDTO> put(@RequestBody CompaniaAseguradoraDTO S) throws ParseException {
    log.info("Actualizando compañia aseguradora");
        companiaAseguradoraService.update(S);
        return ResponseEntity.ok(S);
    }
    @Operation(summary = "Eliminar compañia aseguradora",
            operationId = "deleteCompaniaAseguradora",
            description = "Este endpoint elimina una compañia aseguradora", tags = {"CompaniaAseguradoraRestController"})
    @ApiResponse(responseCode = "200", description = "Compañia aseguradora eliminada correctamente")
    @DeleteMapping(value = "/companiaAseguradora/DeleteCompaniaAseguradora/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ParseException {
    log.info("Eliminando compañia aseguradora con ID: {}", id);
        companiaAseguradoraService.delete(id);
        return ResponseEntity.ok("Compañia aseguradora eliminada correctamente");
    }
}
