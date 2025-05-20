package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.PiezaDTO;
import com.cdh.apitaller.entitys.Pieza;

import java.util.List;

public interface PiezaService {
    void add(PiezaDTO piezaDTO);
    void update(PiezaDTO piezaDTO);
    void delete(PiezaDTO piezaDTO);
    Object findById(Long id);
    List<Pieza> findAll();
}
