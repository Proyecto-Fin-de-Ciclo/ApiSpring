package com.cdh.apitaller.dtos;

public record PiezaDTO(
        Long id,
        String Nombre,
        String descripcion,
        double precio,
        int stock,
        String proveedor,
        String Referencia
) {}
