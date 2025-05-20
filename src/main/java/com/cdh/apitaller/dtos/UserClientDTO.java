package com.cdh.apitaller.dtos;

public record UserClientDTO(
        Long id,
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
