package com.cdh.apitaller.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Marca", length = 15, nullable = false)
    private String marca;

    @Column(name = "Modelo", length = 15, nullable = false)
    private String modelo;

    @Column(name = "Matricula", length = 8, unique = true, nullable = false)
    private String matricula;

    @Column(name = "Color", length = 15, nullable = false)
    private String color;

    @Column(name = "Numero de Bastidor", length = 17, nullable = false)
    private String numeroBastidor;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "propetario_id", nullable = false)
    @JsonIgnoreProperties({"vehiculos"})
    @ToString.Exclude
    private User propietario;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "companiaAseguradora_id", nullable = false)
    private CompaniaAseguradora companiaAseguradora;

    @OneToMany(mappedBy = "vehiculoCita")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Cita> citas;
}
