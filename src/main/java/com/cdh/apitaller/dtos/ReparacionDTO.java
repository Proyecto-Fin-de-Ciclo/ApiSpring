package com.cdh.apitaller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record ReparacionDTO(
        @Schema(hidden=true) Long id,
        String descripcion,
        TrabajadorDTO trabajadorDTO,
        LocalDateTime horaInicio,
        LocalDateTime horaFin,
        @Schema(hidden=true)List<Long> piezasIds
) {}
