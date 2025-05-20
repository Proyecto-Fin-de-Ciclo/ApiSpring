package com.cdh.apitaller.dtos;

public record VehiculoDTO(
        Long id,
        String marca,
        String modelo,
        String matricula,
        String color,
        String numeroBastidor,
        Long propietarioId,
        Long companiaAseguradoraId
) {}