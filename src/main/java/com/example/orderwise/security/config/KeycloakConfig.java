package com.example.orderwise.security.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {
    static Keycloak keycloak = null;

    private final AppProperties appProperties;

    public KeycloakConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public Keycloak getInstance(){
        if (keycloak == null) {
            ResteasyClientBuilder clientBuilder = (ResteasyClientBuilder) ResteasyClientBuilder.newBuilder();
            ResteasyClient client = clientBuilder.connectionPoolSize(10).build();

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(appProperties.getAuthServerUrl())
                    .realm(appProperties.getRealm())
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(appProperties.getUsername())
                    .password(appProperties.getPassword())
                    .clientId(appProperties.getClientId())
                    .clientSecret(appProperties.getClientSecret())
                    .resteasyClient(client)
                    .build();
        }
        return keycloak;
    }
}
