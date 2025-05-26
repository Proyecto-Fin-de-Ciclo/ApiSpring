package com.cdh.apitaller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record PiezaDTO(
        @Schema(hidden=true) Long id,
        String nombre,
        String descripcion,
        double precio,
        int stock,
        String proveedor,
        String referencia
) {}
