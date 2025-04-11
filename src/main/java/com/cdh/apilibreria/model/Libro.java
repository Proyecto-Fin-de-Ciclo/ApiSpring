package com.cdh.apilibreria.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String nombre;
    @NonNull
    @JsonProperty("ISBN")
    public String ISBN;

    public String imgName;
    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "autor_id")
    public Autor autor;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tema_id")
    public Temas tema;

    public double precio;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "edicion_id")
    public Edicion edicion;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "formato_id")
    public Formato formato;

    public int cantidad;
    

    public Libro(String nombre, @NonNull String ISBN, String imgName, @NonNull Autor autor, @NonNull Temas tema, double precio, @NonNull Edicion edicion, @NonNull Formato formato,  int cantidad) {
        this.nombre = nombre;
        this.ISBN = ISBN;
        this.imgName = imgName;
        this.autor = autor;
        this.tema = tema;
        this.edicion = edicion;
        this.precio = precio;
        this.formato = formato;
        this.cantidad = cantidad;
    }
}
