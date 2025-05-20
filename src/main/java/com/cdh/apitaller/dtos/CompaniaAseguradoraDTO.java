package com.cdh.apitaller.dtos;

public record CompaniaAseguradoraDTO(
        Long id,
        String nombre,
        String telefono,
        String email,
        boolean concertado
) {}
