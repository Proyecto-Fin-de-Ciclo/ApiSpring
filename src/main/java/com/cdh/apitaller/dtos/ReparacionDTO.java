package com.cdh.apitaller.dtos;

import com.cdh.apitaller.entitys.Pieza;
import com.cdh.apitaller.enums.EstadoReparacion;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record ReparacionDTO(
        @Schema(hidden=true) Long id,
        String descripcion,
        @JsonProperty("trabajadorDTO") TrabajadorDTO trabajador,
        @JsonProperty("userDTO") UserDTO user,
        LocalDateTime horaInicio,
        LocalDateTime horaFin,
        EstadoReparacion estado,
        @Schema(hidden=true) List<Pieza> piezas
) {}
