package com.example.orderwise.security.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@KeycloakConfiguration
@EnableWebSecurity
public class KeycloakSecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Configure Keycloak authentication provider
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = new KeycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        // Use KeycloakSpringBootConfigResolver to load Keycloak configuration from application.properties or application.yml
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        // Use a stateless session strategy since we're using JWT tokens
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configure authorization rules
                .authorizeHttpRequests((authz) -> authz
                        // Allow unauthenticated access to specific endpoints
                        .requestMatchers("**").permitAll()

                        // All other requests must be authenticated
                        .anyRequest().authenticated()
                )
                // Enable CORS with default settings (optional, based on your needs)
                .cors(withDefaults())
                // Disable CSRF since we're using JWT tokens
                .csrf(AbstractHttpConfigurer::disable)
                // Configure session management to stateless (important for APIs using JWT)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Handle unauthorized requests
                .exceptionHandling((exceptions) -> exceptions.authenticationEntryPoint(new Http403ForbiddenEntryPoint()));

        return http.build();
    }
}
