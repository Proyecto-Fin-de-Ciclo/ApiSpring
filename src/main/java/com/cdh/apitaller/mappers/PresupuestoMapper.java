package com.cdh.apitaller.mappers;

import com.cdh.apitaller.dtos.PresupuestoDTO;
import com.cdh.apitaller.entitys.Presupuesto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PresupuestoMapper {

        Presupuesto dtoToEntity(PresupuestoDTO presupuestoDTO);
        PresupuestoDTO entityToDto(Presupuesto presupuesto);

    }


