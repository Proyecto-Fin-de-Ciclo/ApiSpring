package com.cdh.apitaller.dtos;

import com.cdh.apitaller.entitys.UserClient;
import com.cdh.apitaller.entitys.Vehiculo;
import jakarta.persistence.*;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record CitaDTO(Long id,
                      UserClient userClient,
                      Vehiculo vehiculoCita,
                      LocalDate fecha,
                      LocalTime hora) {


}
