package com.cdh.apitaller.entitys;

import com.cdh.apitaller.model.enums.EstadoReparacion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Descripcion", length = 255, nullable = false)
    private String descripcion;

    private Trabajador trabajador;

    @Column(name = "Hora de Inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "Hora de Fin", nullable = false)
    private LocalTime horaFin;

    @Column(name = "Dia de la reparacion", nullable = false)
    private LocalDate diaReparacion;

    @ManyToMany
    @JoinTable(
            name = "vehiculo_pieza",
            joinColumns = @JoinColumn(name = "reparacion_id"),
            inverseJoinColumns = @JoinColumn(name = "pieza_id")
    )
    private List<Pieza> piezas;
}
