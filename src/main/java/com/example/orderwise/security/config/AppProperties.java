package com.example.orderwise.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "keycloak")
@Configuration
public class AppProperties {
    private String realm;
    private String authServerUrl;
    private String resource;
    private boolean publicClient;
    private boolean bearerOnly;
    private String secret;
    private String username;
    private String password;
}
