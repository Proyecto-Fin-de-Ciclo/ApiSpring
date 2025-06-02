package com.cdh.apitaller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record CitaDTO(@Schema(hidden=true) Long id,
                      UserDTO user,
                      @Schema(hidden=true) LocalDateTime fecha
                      ) {


}
