package com.cdh.apitaller.entitys;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Pieza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", length = 55, nullable = false)
    private String Nombre;

    @Column(name = "Descripcion", length = 255, nullable = false)
    private String descripcion;

    @Column(name = "Precio", nullable = false)
    private double precio;

    @Column(name = "Stock", nullable = false)
    private int stock;

    @Column(name = "Proveedor", length = 15, nullable = false)
    private String proveedor;

    @Column(name = "Referencia", length = 15, nullable = false)
    private String Referencia;


}
