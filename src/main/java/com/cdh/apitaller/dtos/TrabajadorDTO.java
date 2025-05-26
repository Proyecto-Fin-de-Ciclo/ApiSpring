package com.cdh.apitaller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record TrabajadorDTO(
        @Schema(hidden=true) Long id,
        String nombreCompleto,
        String codigoEmpleado,
        String imagen,
        String telefono
) {}
