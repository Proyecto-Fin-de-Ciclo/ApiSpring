package com.cdh.apitaller.mappers;

import com.cdh.apitaller.dtos.UserDTO;
import com.cdh.apitaller.entitys.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

      UserDTO entityToDto(User user);
      User dtoToEntity(UserDTO userDTO);
}
