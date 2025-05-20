package com.cdh.apitaller.mappers;

import com.cdh.apitaller.dtos.CitaDTO;
import com.cdh.apitaller.dtos.UserClientDTO;
import com.cdh.apitaller.entitys.Cita;
import com.cdh.apitaller.entitys.UserClient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserClientMapper {

      UserClientDTO entityToDto(UserClient userClient);
      UserClient dtoToEntity(UserClientDTO userClientDTO);
}
