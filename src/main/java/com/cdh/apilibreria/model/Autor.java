package com.cdh.apilibreria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String nombre;
    public String imgName;
    @Column(length = 10000)
    public String descripcion;
    
    @OneToMany(mappedBy = "autor")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Libro> libros;

	@Override
	public String toString() {
		return "Autor [nombre=" + nombre + ", imgName=" + imgName + ", descripcion=" + descripcion + "]";
	}

	public Autor(@NonNull String nombre, String imgName, String descripcion) {
		super();
		this.nombre = nombre;
		this.imgName = imgName;
		this.descripcion = descripcion;
	}

}
