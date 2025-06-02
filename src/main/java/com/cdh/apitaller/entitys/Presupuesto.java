package com.cdh.apitaller.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Presupuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Datos del taller
    private String nombreTaller;
    private String direccionTaller;
    private String telefonoTaller;

    // Datos de la orden
    private String descripcionTrabajo;

    private double subtotalPiezas;
    private double totalConIVA;

    private boolean aceptado;

    @ManyToMany
    @JoinTable(
            name = "presupuesto_piezas",
            joinColumns = @JoinColumn(name = "presupuesto_id"),
            inverseJoinColumns = @JoinColumn(name = "pieza_id")
    )
    private List<Pieza> piezas;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

}

