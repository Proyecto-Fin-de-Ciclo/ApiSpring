package com.cdh.apitaller.mappers;

import com.cdh.apitaller.dtos.CitaDTO;
import com.cdh.apitaller.dtos.PiezaDTO;
import com.cdh.apitaller.entitys.Cita;
import com.cdh.apitaller.entitys.Pieza;
import com.cdh.apitaller.services.PiezaService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PiezaMapper {

     PiezaDTO entityToDto(Pieza pieza);
     Pieza dtoToEntity(PiezaDTO piezaDTO);
}
