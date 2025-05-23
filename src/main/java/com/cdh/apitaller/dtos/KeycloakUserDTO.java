package com.cdh.apitaller.dtos;

import java.util.List;

public record KeycloakUserDTO(
        String username,
        boolean enabled,
        String email,
        List<KeycloakCredentialDTO> credentials
) {}
