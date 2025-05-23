package com.cdh.apitaller.mappers;

import com.cdh.apitaller.dtos.KeycloakUserDTO;
import com.cdh.apitaller.dtos.UserDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface KeycloackMapper {

    public KeycloakUserDTO mapToKeyCloakUser(UserDTO userDTO);
}
