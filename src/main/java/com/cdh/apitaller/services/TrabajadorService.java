package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.TrabajadorDTO;
import com.cdh.apitaller.entitys.Trabajador;

import java.util.List;

public interface TrabajadorService {
    void add(TrabajadorDTO trabajadorDTO);
    void update(TrabajadorDTO trabajadorDTO);
    void delete(Long id);
    Trabajador findById(Long id);
    List<Trabajador> findAll();
    Trabajador getTrabajadorByNombreUsuarioApp(String nombreUsuarioApp);
}
