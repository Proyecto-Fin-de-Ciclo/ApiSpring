package com.cdh.apitaller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserDTO(
        @Schema(hidden=true) Long id,
        String nombre,
        String apellidos,
        String dni,
        int telefono,
        String email,
        String direccion,
        String ciudad,
        String provincia,
        String cp,
        String pais,
        String nombreUsuarioApp,
        String password
) {}
