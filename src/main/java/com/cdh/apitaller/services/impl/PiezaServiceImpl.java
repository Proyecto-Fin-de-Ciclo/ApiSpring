package com.cdh.apitaller.services.impl;

import com.cdh.apitaller.dtos.PiezaDTO;
import com.cdh.apitaller.entitys.Pieza;
import com.cdh.apitaller.mappers.PiezaMapper;
import com.cdh.apitaller.repository.PiezaRepository;
import com.cdh.apitaller.services.PiezaService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PiezaServiceImpl  implements PiezaService {
    private final PiezaMapper piezaMapper;
    private final PiezaRepository piezaRepository;
    private static final Logger log = LoggerFactory.getLogger(PiezaServiceImpl.class);

    public PiezaServiceImpl(PiezaMapper piezaMapper, PiezaRepository piezaRepository) {
        this.piezaMapper = piezaMapper;
        this.piezaRepository = piezaRepository;
    }

    @Override
    public void add(PiezaDTO piezaDTO) {
        if (piezaDTO.id() != null) {
            Optional<Pieza> byId = piezaRepository.findById(piezaDTO.id());
            if (byId.isPresent()) {
                throw new RuntimeException("Pieza with id " + piezaDTO.id() + " already exists");
            }
        }
        log.info("==> Recibido PiezaDTO: {}",piezaDTO);
        Pieza pieza = piezaMapper.dtoToEntity(piezaDTO);
        log.info("==> Recibido PiezaDTO: {}",pieza);
        piezaRepository.save(pieza);
    }

    @Override
    public void update(PiezaDTO piezaDTO) {
        Optional<Pieza> byId = piezaRepository.findById(piezaDTO.id());
        if (byId.isEmpty()) {
            throw new RuntimeException("Pieza with id " + piezaDTO.id() + " does not exist");
        }
        Pieza pieza = piezaMapper.dtoToEntity(piezaDTO);
        piezaRepository.save(pieza);
        }

    @Override
    public void delete(Long id) {
    Optional<Pieza> byId = piezaRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("Pieza with id " + id + " does not exist");
        }
        piezaRepository.delete(byId.get());
    }

    @Override
    public Pieza findById(Long id) {
        return piezaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pieza with id " + id + " does not exist"));
    }

    @Override
    public List<Pieza> findAll() {
        List<Pieza> piezaList = piezaRepository.findAll();
        if (piezaList.isEmpty()) {
            throw new RuntimeException("No hay piezas");
        }
        return piezaList;
    }
}
