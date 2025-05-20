package com.cdh.apitaller.mappers;

import com.cdh.apitaller.dtos.CitaDTO;
import com.cdh.apitaller.dtos.TrabajadorDTO;
import com.cdh.apitaller.entitys.Cita;
import com.cdh.apitaller.entitys.Trabajador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrabajadorMapper {

     TrabajadorDTO entityToDto(Trabajador trabajador);
     Trabajador dtoToEntity(TrabajadorDTO trabajadorDTO);
}
