package com.cdh.apitaller.entitys;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class Pieza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", length = 55, nullable = false)
    private String nombre;

    @Column(name = "Descripcion", length = 255, nullable = false)
    private String descripcion;

    @Column(name = "Precio", nullable = false)
    private double precio;

    @Column(name = "Stock", nullable = false)
    private int stock;

    @Column(name = "Proveedor", length = 150, nullable = false)
    private String proveedor;

    @Column(name = "Referencia", length = 15, nullable = false)
    private String referencia;

    public Pieza(String nombre, String descripcion, double precio, int stock, String proveedor, String referencia) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.proveedor = proveedor;
        this.referencia = referencia;
    }

}
