package com.cdh.apilibreria.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String nombre;
    @NonNull
    private String apellidos;
    @NonNull
    private String cp;
    @NonNull
    private String direccion;
    @JsonProperty("DNI")
    @Column(unique = true, nullable = false)
    private String DNI;
    
    private String email;
    
    private String pwd;
    
    private String rol;
    
   
    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<DatosBank> datosBank;

	public User(@NonNull String nombre, @NonNull String apellidos, @NonNull String cp, @NonNull String direccion,
			@NonNull String DNI, String email, String pwd, String rol) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.cp = cp;
		this.direccion = direccion;
		this.DNI = DNI;
		this.email = email;
		this.pwd = pwd;
		this.rol = rol;
	}

}
