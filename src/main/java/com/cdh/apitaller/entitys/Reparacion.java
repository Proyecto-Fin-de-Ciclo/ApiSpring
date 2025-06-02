package com.cdh.apitaller.entitys;

import com.cdh.apitaller.enums.EstadoReparacion;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn(name = "trabajador_id")
    private Trabajador trabajador;

    @Column(name = "Hora de Inicio", nullable = false)
    private LocalDateTime horaInicio;

    @Column(name = "Hora de Fin")
    private LocalDateTime horaFin;

    @Enumerated(EnumType.STRING)
    private EstadoReparacion estado;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToMany
    @JoinTable(
            name = "vehiculo_pieza",
            joinColumns = @JoinColumn(name = "reparacion_id"),
            inverseJoinColumns = @JoinColumn(name = "pieza_id")
    )
    private List<Pieza> piezas;

    public void addPieza(Pieza pieza){
        if (this.piezas == null) {
            this.piezas = new ArrayList<>();
        }
        piezas.add(pieza);
    }
}
