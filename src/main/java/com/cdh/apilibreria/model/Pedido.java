package com.cdh.apilibreria.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Libro> libros = new ArrayList<>();

    
    private Long unidades;
    
    private Long precioTotal;
    
    @NonNull
    private LocalDate fechaCompra;
   
    
	public Pedido(User user, List<Libro> libros, Long unidades,LocalDate fechaCompra,Long precioTotal) {
		super();
		this.user = user;
		this.libros = libros;
		this.unidades = unidades;
		this.fechaCompra = fechaCompra;
		this.precioTotal = precioTotal;
	}
}


