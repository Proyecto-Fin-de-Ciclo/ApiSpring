package com.cdh.apilibreria.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DatosBank {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
//	@JoinColumn(name = "user_DNI", referencedColumnName = "DNI")
    private User user;
	
    @NonNull
    private String cardNum;
    
    @NonNull
    private String cvv;
    private String titular;
	public DatosBank(@NonNull User user, @NonNull String cardNum, @NonNull String cvv, String titular) {
		super();
		this.user = user;
		this.cardNum = cardNum;
		this.cvv = cvv;
		this.titular = titular;
	}

}
