package com.cdh.apitaller.dtos;

import com.cdh.apitaller.entitys.Vehiculo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record VehiculoDTO(
        @Schema(hidden=true)Long id,
        String marca,
        String modelo,
        String matricula,
        String color,
        String numeroBastidor,
        UserDTO propietario,
        CompaniaAseguradoraDTO companiaAseguradora
) {}