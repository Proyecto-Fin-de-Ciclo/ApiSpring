package com.cdh.apitaller.mappers;

import com.cdh.apitaller.dtos.KeycloakCredentialDTO;
import com.cdh.apitaller.dtos.KeycloakUserDTO;
import com.cdh.apitaller.dtos.UserDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface KeycloackMapper {

    @Mapping(target = "username", source = "nombreUsuarioApp")
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "credentials", expression = "java(createCredentials(userDTO.password()))")
    KeycloakUserDTO mapToKeyCloakUser(UserDTO userDTO);

    default List<KeycloakCredentialDTO> createCredentials(String password) {
        if (password == null || password.isEmpty()) {
            return List.of();
        }
        return List.of(new KeycloakCredentialDTO("password", password, false));
    }
}

