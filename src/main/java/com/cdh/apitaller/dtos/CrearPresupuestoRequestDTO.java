package com.cdh.apitaller.dtos;

public record CrearPresupuestoRequestDTO(
        ReparacionDTO reparacionDTO,
        String matricula,
        String descripcion
) {
}
