package com.cdh.apitaller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record CompaniaAseguradoraDTO(
        @Schema(hidden=true) Long id,
        String nombre,
        String telefono,
        String email,
        boolean concertado
) {}
