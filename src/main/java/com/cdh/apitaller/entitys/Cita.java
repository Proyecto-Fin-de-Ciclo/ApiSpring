package com.cdh.apitaller.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userClient_id", nullable = false)
    private UserClient userClient;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "vehiculoCita_id", nullable = false)
    private Vehiculo vehiculoCita;

    @Column(name = "Fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "Hora", nullable = false)
    private LocalTime hora;



}
