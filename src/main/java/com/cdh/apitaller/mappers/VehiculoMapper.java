package com.cdh.apitaller.mappers;

import com.cdh.apitaller.dtos.CitaDTO;
import com.cdh.apitaller.dtos.VehiculoDTO;
import com.cdh.apitaller.entitys.Cita;
import com.cdh.apitaller.entitys.Vehiculo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {

     VehiculoDTO entityToDto(Vehiculo vehiculo);
     Vehiculo dtoToEntity(VehiculoDTO vehiculoDTO);
}
