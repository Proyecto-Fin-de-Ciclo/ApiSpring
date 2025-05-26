package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.UserDTO;
import com.cdh.apitaller.entitys.User;

import java.util.List;

public interface UserService {
    void addUser(UserDTO userDTO);
    void updateClient(UserDTO userDTO);
    void deleteClient(Long id);
    User findById(Long id);
    User findByDni(String dni);
    List<User> findAll();
}
