package com.example.orderwise.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.json", encoding = "UTF-8")
@ConfigurationProperties
@Getter
@Setter
public class JsonProperties {
    private String newCustomerSubject;
}
