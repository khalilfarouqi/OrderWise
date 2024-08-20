package com.example.orderwise.common.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.orderwise.security.config.JwtConverter;
import org.springframework.context.annotation.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtConverter jwtConverter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/v1/destinations/getAll").permitAll()
                        .requestMatchers("/v1/config-app/**").permitAll()
                        .requestMatchers("/v1/order/trucking-order/**").permitAll()
                        .requestMatchers("/v1/keycloak/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/contactus").permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/user").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));

        return http.build();
    }
}
