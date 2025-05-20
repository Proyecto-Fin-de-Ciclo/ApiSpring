package com.cdh.apitaller.dtos;

import com.cdh.apitaller.entitys.User;
import com.cdh.apitaller.entitys.Vehiculo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record CitaDTO(@Schema(hidden=true) Long id,
                      UserDTO user,
                      VehiculoDTO vehiculoCita,
                      @Schema(hidden=true) LocalDateTime fecha
                      ) {


}
