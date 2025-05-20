package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.CompaniaAseguradoraDTO;
import com.cdh.apitaller.entitys.CompaniaAseguradora;

import java.util.List;

public interface CompaniaAseguradoraService {
    void add(CompaniaAseguradoraDTO companiaAseguradoraDTO);
    void update(CompaniaAseguradoraDTO companiaAseguradoraDTO);
    void delete(CompaniaAseguradoraDTO companiaAseguradoraDTO);
    CompaniaAseguradora findById(Long id);
    List<CompaniaAseguradora> findAll();
}
