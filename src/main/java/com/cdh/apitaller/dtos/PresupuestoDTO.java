package com.cdh.apitaller.dtos;

import com.cdh.apitaller.entitys.Pieza;
import com.cdh.apitaller.entitys.Vehiculo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PresupuestoDTO(
        Long id,
        String nombreTaller,
        String direccionTaller,
        String telefonoTaller,
        String descripcionTrabajo,
        double subtotalPiezas,
        double totalConIVA,
        boolean aceptado,
        List<Pieza> piezas,
        Vehiculo vehiculo
        ) {}
