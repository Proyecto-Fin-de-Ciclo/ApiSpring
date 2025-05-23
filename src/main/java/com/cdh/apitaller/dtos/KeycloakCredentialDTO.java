package com.cdh.apitaller.dtos;

public record KeycloakCredentialDTO(
        String type,
        String value,
        boolean temporary
) {}
