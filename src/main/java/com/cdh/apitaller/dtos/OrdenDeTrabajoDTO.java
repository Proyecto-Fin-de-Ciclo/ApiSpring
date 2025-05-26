package com.cdh.apitaller.dtos;

import com.cdh.apitaller.enums.EstadoReparacion;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record OrdenDeTrabajoDTO(
        @Schema(hidden=true)Long id,
        List<TrabajadorDTO> trabajadores,
        VehiculoDTO vehiculoDTO,
        String descripcionTrabajo,
        EstadoReparacion estadoOrdenDeTrabajo,
        int horasDeTrabajo
) {}
