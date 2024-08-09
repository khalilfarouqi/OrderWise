package com.example.orderwise.security.config;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.ws.rs.client.Client;

@Configuration
public class KeycloakClientConfig {
    @Value("${app.public-client}")
    private Boolean publicClient;
    @Value("${app.realm}")
    private String realm;
    @Value("${app.resource}")
    private String resource;
    @Value("${app.auth-server-url}")
    private String authServerUrl;
    @Value("${app.secret}")
    private String secret;
    @Value("${app.username}")
    private String username;
    @Value("${app.password}")
    private String password;

    static Keycloak keycloak = null;

    public static String getCurrentUserToken() {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
            return kp.getKeycloakSecurityContext().getTokenString();
        }
        return "No User Found";
    }

    public static AccessToken getUserIDToken(){
        try {
            KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
                KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
                return kp.getKeycloakSecurityContext().getToken();
            } else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    public Keycloak getInstance() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .grantType(OAuth2Constants.PASSWORD)
                .realm(realm)
                .clientId(resource)
                .username(username)
                .password(password)
                .resteasyClient((Client) new ResteasyClientBuilderImpl().connectionPoolSize(10).build())
                .build();
        keycloak.tokenManager().getAccessToken();
        return keycloak;
    }
}
