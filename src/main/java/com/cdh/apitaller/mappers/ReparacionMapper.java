package com.cdh.apitaller.mappers;

import com.cdh.apitaller.dtos.PiezaDTO;
import com.cdh.apitaller.dtos.ReparacionDTO;
import com.cdh.apitaller.entitys.Pieza;
import com.cdh.apitaller.entitys.Reparacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReparacionMapper {

     Reparacion dtoToEntity(ReparacionDTO reparacionDTO);
     ReparacionDTO entityToDto(Reparacion reparacion);

}
