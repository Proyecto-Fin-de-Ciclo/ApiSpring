package com.cdh.apitaller.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", length = 15, nullable = false)
    private String nombre;

    @Column(name = "Apellidos", length = 25, nullable = false)
    private String apellidos;

    @Column(name = "DNI", length = 9, unique = true, nullable = false)
    private String dni;

    @Column(name = "Telefono", length = 9, unique = true, nullable = false)
    private int telefono;

    @Column(name = "Email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name = "Direccion", length = 50, nullable = false)
    private String direccion;

    @Column(name = "Ciudad", length = 25, nullable = false)
    private String ciudad;

    @Column(name = "Provincia", length = 25, nullable = false)
    private String provincia;

    @Column(name = "CP", length = 5, nullable = false)
    private String cp;

    @Column(name = "Pais", length = 25, nullable = false)
    private String pais;

    @Column(name = "NombreUsuarioApp", length = 15, unique = true, nullable = false)
    private String nombreUsuarioApp;

    @Column(name = "Password", length = 25, nullable = false)
    private String password;

    @OneToMany(mappedBy = "propietario")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Vehiculo> vehiculos;

    @OneToMany(mappedBy = "userClient")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Cita> citas;


}
