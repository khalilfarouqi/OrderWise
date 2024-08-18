package com.example.orderwise.security.config;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.server.resource.authentication.*;
import org.springframework.security.oauth2.jwt.*;

import java.util.stream.*;
import java.util.*;

@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    private final JwtConverterProperties properties;

    public static AppProperties appProperties;

    static Keycloak keycloak = null;

    public JwtConverter(JwtConverterProperties properties, AppProperties appProperties) {
        this.properties = properties;
        this.appProperties = appProperties;
    }

    public static Keycloak getInstance() {
        Client resteasyClient = ClientBuilder.newBuilder().build();
        keycloak = KeycloakBuilder.builder()
                .serverUrl(appProperties.getAuthServerUrl())
                .grantType(OAuth2Constants.PASSWORD)
                .realm(appProperties.getRealm())
                .clientId(appProperties.getResource())
                .username(appProperties.getUsername())
                .password(appProperties.getPassword())
                .resteasyClient(resteasyClient)
                .build();
        keycloak.tokenManager().getAccessToken();
        return keycloak;
    }

    public static Keycloak getUserInstance(String username, String password) {
        Client resteasyClient = ClientBuilder.newBuilder().build();
        keycloak = KeycloakBuilder.builder()
                .serverUrl(appProperties.getAuthServerUrl())
                .grantType(OAuth2Constants.PASSWORD)
                .realm(appProperties.getRealm())
                .clientId(appProperties.getResource())
                .username(username)
                .password(password)
                .resteasyClient(resteasyClient)
                .build();
        keycloak.tokenManager().getAccessToken();
        return keycloak;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream
                .concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(), extractResourceRoles(jwt).stream())
                .collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
    }

    private String getPrincipalClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (properties.getPrincipalAttribute() != null) {
            claimName = properties.getPrincipalAttribute();
        }
        return jwt.getClaim(claimName);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> resource;
        Collection<String> resourceRoles;

        if (resourceAccess == null
                || (resource = (Map<String, Object>) resourceAccess.get(properties.getResourceId())) == null
                || (resourceRoles = (Collection<String>) resource.get("roles")) == null) {
            return Set.of();
        }
        return resourceRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}
