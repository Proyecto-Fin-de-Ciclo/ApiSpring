package com.cdh.apitaller.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Trabajador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NombreCompleto", length = 50, nullable = false)
  private String nombreCompleto;

  @Column(name = "CodigoEmpleado", length = 8, unique = true, nullable = false)
  private String codigoEmpleado;

  }