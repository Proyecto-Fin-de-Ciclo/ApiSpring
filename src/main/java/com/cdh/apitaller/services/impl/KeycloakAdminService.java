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
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class KeycloakAdminService {

    private WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String token;

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
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();

        this.token = fetchToken();
    }

    private String fetchToken() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", clientId);
        formData.add("username", username);
        formData.add("password", password);

        Map<String, Object> response = webClient.post()
                .uri("/realms/" + realm + "/protocol/openid-connect/token")
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        return (String) response.get("access_token");
    }

    public void createUser(KeycloakUserDTO keycloakUserDTO) {
        webClient.post()
                .uri("/admin/realms/" + realm + "/users")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .bodyValue(keycloakUserDTO)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
