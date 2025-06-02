package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.CompaniaAseguradoraDTO;
import com.cdh.apitaller.entitys.CompaniaAseguradora;
import com.cdh.apitaller.entitys.User;
import com.cdh.apitaller.entitys.Vehiculo;

import java.util.List;

public interface CompaniaAseguradoraService {
    void add(CompaniaAseguradoraDTO companiaAseguradoraDTO);
    void update(CompaniaAseguradoraDTO companiaAseguradoraDTO);
    void delete(Long id);
    CompaniaAseguradora findById(Long id);

    List<CompaniaAseguradora> findAll();
}
