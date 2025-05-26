package com.cdh.apitaller.controller;

import com.cdh.apitaller.dtos.VehiculoDTO;
import com.cdh.apitaller.entitys.Vehiculo;
import com.cdh.apitaller.services.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@Tag( name = "VehiculoRestController", description = "Controlador para la entidad Vehiculo")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@Slf4j
public class VehiculoRestController{
    private final VehiculoService vehiculoService;
    public VehiculoRestController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }
    @Operation(summary = "Obtener todos los vehiculos",
            operationId = "getAllVehiculos",
            description = "Este endpoint devuelve una lista de todos los vehiculos", tags = {"VehiculoRestController"})
    @ApiResponse(responseCode = "200", description = "Lista de vehiculos obtenida correctamente")
    @GetMapping(value = "/vehiculo/GetAllVehiculos")
    public ResponseEntity<List<Vehiculo>> getAll() throws ParseException {
        log.info("Obteniendo todos los vehiculos");
        List<Vehiculo> vehiculoList = vehiculoService.findAll();
        return ResponseEntity.ok(vehiculoList);
    }
    @Operation(summary = "Obtener vehiculo por ID",
            operationId = "getVehiculoById",
            description = "Este endpoint devuelve un vehiculo por su ID", tags = {"VehiculoRestController"})
    @ApiResponse(responseCode = "200", description = "Vehiculo obtenido correctamente")
    @GetMapping(value = "/vehiculo/GetVehiculoById/{id}")
    public ResponseEntity<Vehiculo> getById(@PathVariable Long id) throws ParseException {
        log.info("Obteniendo vehiculo con ID: {}", id);
        Vehiculo vehiculo = vehiculoService.findById(id);
        return ResponseEntity.ok(vehiculo);
    }
    @Operation(summary = "Obtener vehiculo por matricula",
            operationId = "getVehiculoByMatricula",
            description = "Este endpoint devuelve un vehiculo por su matricula", tags = {"VehiculoRestController"})
    @ApiResponse(responseCode = "200", description = "Vehiculo obtenido correctamente")
    @GetMapping(value = "/vehiculo/GetVehiculoByMatricula/{matricula}")
    public ResponseEntity<Vehiculo> getByMatricula(@PathVariable String matricula) throws ParseException {
        log.info("Obteniendo vehiculo con matricula: {}", matricula);
        Vehiculo vehiculo = vehiculoService.findByMatricula(matricula);
        return ResponseEntity.ok(vehiculo);
    }

    @Operation(summary = "Crear un nuevo vehiculo",
            operationId = "createVehiculo",
            description = "Este endpoint crea un nuevo vehiculo", tags = {"VehiculoRestController"})
    @ApiResponse(responseCode = "201", description = "Vehiculo creado correctamente")
    @PostMapping(value = "/vehiculo/CreateVehiculo")
    public ResponseEntity<VehiculoDTO> post(@RequestBody VehiculoDTO vehiculoDTO) throws ParseException {
        log.info("Creando nuevo vehiculo");
        vehiculoService.addVehiculo(vehiculoDTO);
        return ResponseEntity.ok(vehiculoDTO);
    }

    @Operation(summary = "Actualizar un vehiculo",
            operationId = "updateVehiculo",
            description = "Este endpoint actualiza un vehiculo", tags = {"VehiculoRestController"})
    @ApiResponse(responseCode = "200", description = "Vehiculo actualizado correctamente")
    @PutMapping(value = "/vehiculo/UpdateVehiculo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiculoDTO> put(@RequestBody VehiculoDTO S) throws ParseException {
        log.info("Actualizando vehiculo");
        vehiculoService.updateVehiculo(S);
        return ResponseEntity.ok(S);
    }
    @Operation(summary = "Eliminar un vehiculo",
            operationId = "deleteVehiculo",
            description = "Este endpoint elimina un vehiculo", tags = {"VehiculoRestController"})
    @ApiResponse(responseCode = "200", description = "Vehiculo eliminado correctamente")
    @PostMapping(value = "/vehiculo/DeleteVehiculo/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ParseException {
        log.info("Eliminando vehiculo con ID: {}", id);
        vehiculoService.deleteVehiculo(id);
        return ResponseEntity.ok("Vehiculo eliminado correctamente");
    }
}
