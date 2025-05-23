package com.cdh.apitaller.services.impl;

import com.cdh.apitaller.dtos.CitaDTO;
import com.cdh.apitaller.entitys.Cita;
import com.cdh.apitaller.entitys.User;
import com.cdh.apitaller.mappers.CitaMapper;
import com.cdh.apitaller.repository.CitaRepository;
import com.cdh.apitaller.repository.UserRepository;
import com.cdh.apitaller.services.CitaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CitaServiceImpl implements CitaService {
private final CitaRepository citaRepository;
private final CitaMapper citaMapper;
private final UserRepository userRepository;

    public CitaServiceImpl(CitaRepository citaRepository, CitaMapper citaMapper, UserRepository userRepository) {
        this.citaRepository = citaRepository;
        this.citaMapper = citaMapper;
        this.userRepository = userRepository;
    }
    @Override
    public void add(CitaDTO citaDTO) {
        if (citaDTO.id() != null) {
            Optional<Cita> byId = citaRepository.findById(citaDTO.id());
            if (byId.isPresent()) {
                throw new RuntimeException("Cita with id " + citaDTO.id() + " already exists");
            }
        }

        Cita cita = citaMapper.dtoToEntity(citaDTO);
        Long userId = citaDTO.user().id();
        if (userId == null) {
            throw new RuntimeException("El usuario debe tener un ID");
        }
        Optional<User> userRepositoryById = userRepository.findById(userId);
        userRepositoryById
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID " + userId));
        cita.setUser(userRepositoryById.get());
        citaRepository.save(cita);
        
    }

    @Override
    public void update(CitaDTO citaDTO) {
        Optional<Cita> byId = citaRepository.findById(citaDTO.id());
        if (byId.isEmpty()) {
            throw new RuntimeException("Cita with id " + citaDTO.id() + " does not exist");
        }
        Cita cita = citaMapper.dtoToEntity(citaDTO);
        citaRepository.save(cita);
    }

    @Override
    public void delete(Long id) {
        Optional<Cita> byId = citaRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("Cita with id " + id + " does not exist");
        }
        citaRepository.delete(byId.get());
    }

    @Override
    public Cita findById(Long id) {
        Optional<Cita> byId = citaRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("Cita with id " + id + " does not exist");
        }
        return byId.get();
    }

    @Override
    public List<Cita> findAll() {
        return citaRepository.findAll();

    }

    @Override
    public List<Cita> findAllByFechaBetween(LocalDateTime inicio, LocalDateTime fin) {
        return citaRepository.findAllByFechaBetween(inicio, fin);
    }
}
