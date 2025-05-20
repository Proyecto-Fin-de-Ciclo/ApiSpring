package com.cdh.apitaller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record VehiculoDTO(
        @Schema(hidden=true)Long id,
        String marca,
        String modelo,
        String matricula,
        String color,
        String numeroBastidor,
        UserDTO propietarioId,
        CompaniaAseguradoraDTO companiaAseguradoraId
) {}