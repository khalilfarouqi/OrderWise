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

    private static final AppProperties appProperties = null;

    private static final String serverUrl = appProperties.getAuthServerUrl();
    public static final String realm = appProperties.getRealm();
    private static final String clientId = appProperties.getClientId();
    private static final String clientSecret = appProperties.getClientSecret();
    private static final String userName = appProperties.getUsername();
    private static final String password = appProperties.getPassword();

    public KeycloakConfig() {
    }

    public static Keycloak getInstance(){
        if (keycloak == null) {
            ResteasyClientBuilder clientBuilder = (ResteasyClientBuilder) ResteasyClientBuilder.newBuilder();
            ResteasyClient client = clientBuilder.connectionPoolSize(10).build();

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(client)
                    .build();
        }
        return keycloak;
    }
}
