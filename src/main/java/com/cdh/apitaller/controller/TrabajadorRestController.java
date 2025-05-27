package com.cdh.apitaller.controller;

import com.cdh.apitaller.dtos.ImagenResponseDTO;
import com.cdh.apitaller.dtos.TrabajadorDTO;
import com.cdh.apitaller.entitys.Trabajador;
import com.cdh.apitaller.services.TrabajadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    private static final String UPLOAD_DIR = "uploads/";

    public TrabajadorRestController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @Operation(summary = "Obtener todos los trabajadores",
            operationId = "getAllTrabajadores",
            description = "Obtiene una lista de todos los trabajadores")
    @ApiResponse(responseCode = "200", description = "Lista de trabajadores obtenida correctamente")
    @GetMapping(value = "/trabajador/GetAllTrabajadores")
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
    public ResponseEntity<TrabajadorDTO> post(@RequestBody TrabajadorDTO trabajadorDTO) throws ParseException {
        log.info("Creando nuevo trabajador");
        trabajadorService.add(trabajadorDTO);
        return ResponseEntity.ok(trabajadorDTO);
    }
    @Operation(summary = "Actualizar un trabajador",
            operationId = "updateTrabajador",
            description = "Actualiza un trabajador existente")
    @ApiResponse(responseCode = "200", description = "Trabajador actualizado correctamente")
    @PutMapping(value = "/trabajador/UpdateTrabajador")
    public ResponseEntity<TrabajadorDTO> put(@RequestBody TrabajadorDTO S) throws ParseException {
    log.info("Actualizando trabajador");
        trabajadorService.update(S);
        return ResponseEntity.ok(S);
    }
    @Operation(summary = "Eliminar un trabajador",
            operationId = "deleteTrabajador",
            description = "Elimina un trabajador por su ID")
    @ApiResponse(responseCode = "200", description = "Trabajador eliminado correctamente")
    @DeleteMapping(value = "/trabajador/DeleteTrabajador/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ParseException {
        log.info("Eliminando trabajador con ID: {}", id);
        trabajadorService.delete(id);
        return ResponseEntity.ok("Trabajador eliminado correctamente");
    }

    @PostMapping(value = "/trabajador/uploadImagen", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImagenResponseDTO> subirImagen(
            @Parameter(description = "Archivo de imagen", required = true, content = @Content(schema = @Schema(type = "string", format = "binary")))
            @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(
                        new ImagenResponseDTO("El archivo está vacío", "", "")
                );
            }

            File directorio = new File(UPLOAD_DIR);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            Path rutaArchivo = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.copy(file.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);

            ImagenResponseDTO respuesta = new ImagenResponseDTO(
                    "Imagen subida correctamente",
                    file.getOriginalFilename(),
                    rutaArchivo.toString()
            );

            return ResponseEntity.ok(respuesta);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(
                    new ImagenResponseDTO("Error al subir la imagen", "", "")
            );
        }
    }

    @Operation(summary = "Obtener imagen de trabajador",
            operationId = "getTrabajadorImage",
            description = "Obtiene la imagen de un trabajador por su nombre")
    @ApiResponse(responseCode = "200", description = "Imagen de trabajador obtenida correctamente")
    @GetMapping("/imagen/{fileName}")
    public ResponseEntity<Resource> obtenerImagen(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            Resource recurso = new UrlResource(filePath.toUri());

            if (!recurso.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Determinar tipo MIME para que el navegador sepa qué tipo de archivo es
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(recurso);

        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

