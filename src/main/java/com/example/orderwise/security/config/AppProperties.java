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
    private Boolean publicClient;
    private String realm;
    private String resource;
    private String authServerUrl;
    private String clientId;
    private String clientSecret;
    private String username;
    private String password;
}
