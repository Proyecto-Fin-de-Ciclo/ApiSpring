package com.cdh.apitaller.services.impl;


import com.cdh.apitaller.dtos.KeycloakUserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class KeycloakAdminService {

    private WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${keycloak.url}")
    private String keycloakUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.username}")
    private String username;
    @Value("${keycloak.password}")
    private String password;
    @Value("${keycloak.client-id}")
    private String clientId;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                .baseUrl(keycloakUrl)
                .build();
    }

    private String fetchToken() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", clientId);
        formData.add("username", username);
        formData.add("password", password);

        try {
            Map<String, Object> response = webClient.post()
                    .uri("/realms/" + realm + "/protocol/openid-connect/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                    .block();

            if (response == null || !response.containsKey("access_token")) {
                throw new RuntimeException("No se recibió access_token de Keycloak");
            }

            return (String) response.get("access_token");

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener token de Keycloak: " + e.getMessage(), e);
        }
    }

    public void createUser(KeycloakUserDTO keycloakUserDTO) {
        String token = fetchToken();  // OBTENEMOS TOKEN RECIÉN AQUÍ

        try {
            String json = objectMapper.writeValueAsString(keycloakUserDTO);
            System.out.println("JSON a enviar a Keycloak: " + json);

            webClient.post()
                    .uri("/admin/realms/" + realm + "/users")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(keycloakUserDTO)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();

            System.out.println("Usuario creado correctamente");

        } catch (Exception e) {
            System.err.println("Error creando usuario en Keycloak:");
            e.printStackTrace();
        }
    }
}
