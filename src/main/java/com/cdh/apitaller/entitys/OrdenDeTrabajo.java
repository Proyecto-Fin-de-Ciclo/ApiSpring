package com.cdh.apitaller.entitys;

import com.cdh.apitaller.enums.EstadoReparacion;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDeTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Trabajadores (muchos trabajadores pueden estar asignados a una orden)
    @ManyToMany
    @JoinTable(
            name = "orden_trabajador",
            joinColumns = @JoinColumn(name = "orden_id"),
            inverseJoinColumns = @JoinColumn(name = "trabajador_id")
    )
    private List<Trabajador> Trabajador;

    // Relación con Vehículo (una orden tiene un solo vehículo)
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    private String descripcionTrabajo;

    @Enumerated(EnumType.STRING)
    private EstadoReparacion estadoOrdenDeTrabajo;

    @ManyToMany
    @JoinTable(
            name = "orden_de_trabajo_piezas",
            joinColumns = @JoinColumn(name = "orden_de_trabajo_id"),
            inverseJoinColumns = @JoinColumn(name = "pieza_id")
    )
    private List<Pieza> piezas;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private User user;

}

