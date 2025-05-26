package com.cdh.apitaller.controller;

import com.cdh.apitaller.dtos.OrdenDeTrabajoDTO;
import com.cdh.apitaller.dtos.ReparacionDTO;
import com.cdh.apitaller.entitys.OrdenDeTrabajo;
import com.cdh.apitaller.entitys.Reparacion;
import com.cdh.apitaller.mappers.ReparacionMapper;
import com.cdh.apitaller.services.OrdenDeTrabajoService;
import com.cdh.apitaller.services.ReparacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
@RestController
@Tag(name = "OrdenDeTrabajoRestController", description = "Controlador para la entidad Orden de Trabajo")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@Slf4j
public class OrdenDeTrabajoController {
    private final OrdenDeTrabajoService ordenDeTrabajoService;
    private final ReparacionService reparacionService;
    private final ReparacionMapper reparacionMapper;


    public OrdenDeTrabajoController(OrdenDeTrabajoService ordenDeTrabajoService, ReparacionService reparacionService, ReparacionMapper reparacionMapper) {
        this.ordenDeTrabajoService = ordenDeTrabajoService;
        this.reparacionService = reparacionService;
        this.reparacionMapper = reparacionMapper;
    }
    @Operation(summary = "Obtener todas las órdenes de trabajo",
            operationId = "getAllOrdenesDeTrabajo",
            description = "Este endpoint devuelve una lista de todas las órdenes de trabajo", tags = {"OrdenDeTrabajoController"})
    @ApiResponse(responseCode = "200", description = "Lista de órdenes de trabajo obtenida correctamente")
    @GetMapping(value = "/ordenDeTrabajo/GetAllOrdenesDeTrabajo")
    public ResponseEntity<List<OrdenDeTrabajo>> getAll() throws ParseException {
        log.info("Obteniendo todas las órdenes de trabajo");
        List<OrdenDeTrabajo> ordenesDeTrabajo = ordenDeTrabajoService.findAll();
        return ResponseEntity.ok(ordenesDeTrabajo);
    }

    @Operation(summary = "Obtener orden de trabajo por ID",
            operationId = "getOrdenDeTrabajoById",
            description = "Este endpoint devuelve una orden de trabajo por su ID", tags = {"OrdenDeTrabajoController"})
    @ApiResponse(responseCode = "200", description = "Orden de trabajo obtenida correctamente")
    @GetMapping(value = "/ordenDeTrabajo/GetOrdenDeTrabajoById/{id}")
    public ResponseEntity<OrdenDeTrabajo> getById(Long id) throws ParseException {
        log.info("Obteniendo orden de trabajo con ID: {}", id);
        OrdenDeTrabajo ordenDeTrabajo = ordenDeTrabajoService.findById(id);
        return ResponseEntity.ok(ordenDeTrabajo);
    }

    @Operation(summary = "Crear una nueva orden de trabajo",
            operationId = "createOrdenDeTrabajo",
            description = "Este endpoint crea una nueva orden de trabajo", tags = {"OrdenDeTrabajoController"})
    @ApiResponse(responseCode = "200", description = "Orden de trabajo creada correctamente")
    @PostMapping(value = "/ordenDeTrabajo/CreateOrdenDeTrabajo")
    public ResponseEntity<OrdenDeTrabajoDTO> post(OrdenDeTrabajoDTO ordenDeTrabajoDTO) throws ParseException {
        log.info("Creando nueva orden de trabajo");
        ordenDeTrabajoService.add(ordenDeTrabajoDTO);
        return ResponseEntity.ok(ordenDeTrabajoDTO);

    }
    @Operation(summary = "Actualizar una orden de trabajo",
            operationId = "updateOrdenDeTrabajo",
            description = "Este endpoint actualiza una orden de trabajo existente", tags = {"OrdenDeTrabajoController"})
    @ApiResponse(responseCode = "200", description = "Orden de trabajo actualizada correctamente")
    @PostMapping(value = "/ordenDeTrabajo/UpdateOrdenDeTrabajo")
    public ResponseEntity<OrdenDeTrabajoDTO> put(OrdenDeTrabajoDTO S) throws ParseException {
        log.info("Actualizando orden de trabajo");
        ordenDeTrabajoService.update(S);
        return ResponseEntity.ok(S);
    }

    @Operation(summary = "Establecer fecha de fin de reparación",
            operationId = "establecerFechaFinInReparacion",
            description = "Este endpoint establece la fecha de fin de una reparación por su ID", tags = {"OrdenDeTrabajoController"})
    @ApiResponse(responseCode = "200", description = "Fecha de fin de reparación actualizada correctamente")
    @PostMapping(value = "/ordenDeTrabajo/EstablecerFechaFinInReparacion/{id}")
    public ResponseEntity<String> EstablecerFechaFinInReparacion(Long id) throws ParseException {
        log.info("Actualizando fecha de reparación para la orden de trabajo con ID: {}", id);
        Reparacion byId = reparacionService.findById(id);
        if (byId == null) {
            log.error("No se encontró la reparación con ID: {}", id);
            return ResponseEntity.notFound().build();
        }
        byId.setHoraFin(LocalDateTime.now());
        ReparacionDTO reparacionDTO = reparacionMapper.entityToDto(byId);
        reparacionService.update(reparacionDTO);
        return ResponseEntity.ok("Fecha de reparación actualizada correctamente");
    }
    @Operation(summary = "Eliminar una orden de trabajo",
            operationId = "deleteOrdenDeTrabajo",
            description = "Este endpoint elimina una orden de trabajo por su ID", tags = {"OrdenDeTrabajoController"})
    @ApiResponse(responseCode = "200", description = "Orden de trabajo eliminada correctamente")
    @DeleteMapping(value = "/ordenDeTrabajo/DeleteOrdenDeTrabajo/{id}")
    public ResponseEntity<String> delete(Long id) throws ParseException {
        log.info("Eliminando orden de trabajo con ID: {}", id);
        ordenDeTrabajoService.delete(id);
        return ResponseEntity.ok("Orden de trabajo eliminada correctamente");
    }
}
