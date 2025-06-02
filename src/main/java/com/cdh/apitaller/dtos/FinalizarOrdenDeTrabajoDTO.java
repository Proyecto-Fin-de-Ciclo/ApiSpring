package com.cdh.apitaller.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record FinalizarOrdenDeTrabajoDTO(
        Long id,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime fechaFin,
        OrdenDeTrabajoDTO ordenDeTrabajoDTO
) {}
