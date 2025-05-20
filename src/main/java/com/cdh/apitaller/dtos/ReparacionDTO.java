package com.cdh.apitaller.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ReparacionDTO(
        Long id,
        String descripcion,
        Long trabajadorId,
        LocalTime horaInicio,
        LocalTime horaFin,
        LocalDate diaReparacion,
        List<Long> piezasIds
) {}
