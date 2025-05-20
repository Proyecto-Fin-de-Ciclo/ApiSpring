package com.cdh.apitaller.services.impl;

import com.cdh.apitaller.dtos.CompaniaAseguradoraDTO;
import com.cdh.apitaller.entitys.CompaniaAseguradora;
import com.cdh.apitaller.mappers.CompaniaAseguradoraMapper;
import com.cdh.apitaller.repository.CompaniaAseguradoraRepository;
import com.cdh.apitaller.services.CompaniaAseguradoraService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniaAseguradoraServiceImpl implements CompaniaAseguradoraService {
    private final CompaniaAseguradoraRepository companiaAseguradoraRepository;
    private final CompaniaAseguradoraMapper companiaAseguradoraMapper;

    public CompaniaAseguradoraServiceImpl(CompaniaAseguradoraRepository companiaAseguradoraRepository,
                                          CompaniaAseguradoraMapper companiaAseguradoraMapper) {
        this.companiaAseguradoraRepository = companiaAseguradoraRepository;
        this.companiaAseguradoraMapper = companiaAseguradoraMapper;
    }

    @Override
    public void add(CompaniaAseguradoraDTO companiaAseguradoraDTO) {
        Optional<CompaniaAseguradora> byId = companiaAseguradoraRepository.findById(companiaAseguradoraDTO.id());

        if (byId.isPresent()) {
            throw new RuntimeException("CompaniaAseguradora with id " + companiaAseguradoraDTO.id() + " already exists");
        }
        CompaniaAseguradora companiaAseguradora = companiaAseguradoraMapper.dtoToEntity(companiaAseguradoraDTO);
        companiaAseguradoraRepository.save(companiaAseguradora);

    }

    @Override
    public void update(CompaniaAseguradoraDTO companiaAseguradoraDTO) {
        Optional<CompaniaAseguradora> byId = companiaAseguradoraRepository.findById(companiaAseguradoraDTO.id());
        if (byId.isEmpty()) {
            throw new RuntimeException("CompaniaAseguradora with id " + companiaAseguradoraDTO.id() + " does not exist");
        }
        CompaniaAseguradora companiaAseguradora = companiaAseguradoraMapper.dtoToEntity(companiaAseguradoraDTO);
        companiaAseguradoraRepository.save(companiaAseguradora);
    }

    @Override
    public void delete(CompaniaAseguradoraDTO companiaAseguradoraDTO) {
        Optional<CompaniaAseguradora> byId = companiaAseguradoraRepository.findById(companiaAseguradoraDTO.id());
        if (byId.isEmpty()) {
            throw new RuntimeException("CompaniaAseguradora with id " + companiaAseguradoraDTO.id() + " does not exist");
        }
        CompaniaAseguradora companiaAseguradora = companiaAseguradoraMapper.dtoToEntity(companiaAseguradoraDTO);
        companiaAseguradoraRepository.delete(companiaAseguradora);

    }

    @Override
    public CompaniaAseguradora findById(Long id) {
        Optional<CompaniaAseguradora> byId = companiaAseguradoraRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("CompaniaAseguradora with id " + id + " does not exist");
        }
        return byId.get();
    }

    @Override
    public List<CompaniaAseguradora> findAll() {
        List<CompaniaAseguradora> all = companiaAseguradoraRepository.findAll();
        if (all.isEmpty()) {
            throw new RuntimeException("No hay compania aseguradora");
        }
        return all;
    }
}
