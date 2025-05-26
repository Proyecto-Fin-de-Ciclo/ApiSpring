package com.cdh.apitaller.mappers;

import com.cdh.apitaller.dtos.CompaniaAseguradoraDTO;
import com.cdh.apitaller.dtos.OrdenDeTrabajoDTO;
import com.cdh.apitaller.entitys.CompaniaAseguradora;
import com.cdh.apitaller.entitys.OrdenDeTrabajo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdenDeTrabajoMapper {

    OrdenDeTrabajoDTO entityToDto(OrdenDeTrabajo ordenDeTrabajo);
    OrdenDeTrabajo dtoToEntity(OrdenDeTrabajoDTO ordenDeTrabajoDTO);
}
