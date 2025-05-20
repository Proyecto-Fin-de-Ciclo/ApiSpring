package com.cdh.apitaller.entitys;

import com.cdh.apitaller.entitys.Vehiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class
CompaniaAseguradora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "Nombre", length = 15, unique = true, nullable = false)
    private String nombre;

    @Column(name = "Direccion", length = 9, nullable = false)
    private String telefono;

    @Column(name = "Email", length = 9, nullable = false)
    private String email;

    @Column(name = "concertado", nullable = false)
    private boolean concertado;

    @OneToMany(mappedBy = "companiaAseguradora")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Vehiculo> vehiculos;
}
