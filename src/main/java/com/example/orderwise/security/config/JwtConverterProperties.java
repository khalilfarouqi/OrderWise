package com.example.orderwise.security.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.*;

@Data
@Component
@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt.auth.converter")
public class JwtConverterProperties {
    private String resourceId;
    private String principalAttribute;
}
