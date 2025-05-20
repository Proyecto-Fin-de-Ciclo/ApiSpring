package com.cdh.apitaller.services;

import com.cdh.apitaller.dtos.UserClientDTO;

import java.util.List;

public interface UserClientService {
    void addUserClient(UserClientDTO userClientDTO);
    void updateUserClient(UserClientDTO userClientDTO);
    void deleteUserClient(UserClientDTO userClientDTO);
    Object findById(Long id);
    List<Object> findAll();
}
