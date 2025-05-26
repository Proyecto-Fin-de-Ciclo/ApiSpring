package com.cdh.apitaller.services.impl;


import com.cdh.apitaller.dtos.KeycloakUserDTO;
import com.cdh.apitaller.dtos.UserDTO;
import com.cdh.apitaller.entitys.User;
import com.cdh.apitaller.mappers.KeycloackMapper;
import com.cdh.apitaller.mappers.UserMapper;
import com.cdh.apitaller.repository.UserRepository;
import com.cdh.apitaller.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KeycloakAdminService keycloakAdminService;
    private final KeycloackMapper keycloackMapper;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, KeycloakAdminService keycloakAdminService, KeycloackMapper keycloackMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.keycloakAdminService = keycloakAdminService;
        this.keycloackMapper = keycloackMapper;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        KeycloakUserDTO kcUser = keycloackMapper.mapToKeyCloakUser(userDTO);
        keycloakAdminService.createUser(kcUser);

        if (userDTO.id() != null) {
            Optional<User> userRepositoryById = userRepository.findById(userDTO.id());
            if (userRepositoryById.isPresent()) {
                throw new RuntimeException("User already exists");
            }
        }
        User user = userMapper.dtoToEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    public void updateClient(UserDTO userDTO) {
        Optional<User> userRepositoryById = userRepository.findById(userDTO.id());
        if (userRepositoryById.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userMapper.dtoToEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    public void deleteClient(Long id) {
        Optional<User> userRepositoryById = userRepository.findById(id);
        if (userRepositoryById.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        userRepository.delete(userRepositoryById.get());
    }

    @Override
    public User findById(Long id) {
        Optional<User> userRepositoryById = userRepository.findById(id);
        if (userRepositoryById.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return userRepositoryById.get();

    }

    @Override
    public User findByDni(String dni) {
        User user = userRepository.findByDni(dni);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return userList;
    }
}
