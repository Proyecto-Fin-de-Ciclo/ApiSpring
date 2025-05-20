package com.cdh.apitaller.mappers;

import com.cdh.apitaller.dtos.CitaDTO;
import com.cdh.apitaller.dtos.CompaniaAseguradoraDTO;
import com.cdh.apitaller.entitys.Cita;
import com.cdh.apitaller.entitys.CompaniaAseguradora;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompaniaAseguradoraMapper {

     CompaniaAseguradoraDTO entityToDto(CompaniaAseguradora companiaAseguradora);
     CompaniaAseguradora dtoToEntity(CompaniaAseguradoraDTO companiaAseguradoraDTO);
}
