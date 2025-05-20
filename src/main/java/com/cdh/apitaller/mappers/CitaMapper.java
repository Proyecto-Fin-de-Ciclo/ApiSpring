package com.cdh.apitaller.mappers;

import com.cdh.apitaller.dtos.CitaDTO;
import com.cdh.apitaller.entitys.Cita;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CitaMapper {

     CitaDTO entityToDto(Cita cita);
     Cita dtoToEntity(CitaDTO citaDTO);
}
