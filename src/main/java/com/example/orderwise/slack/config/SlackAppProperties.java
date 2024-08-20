package com.example.orderwise.slack.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "slack")
@Configuration
public class SlackAppProperties {
    private String webhookUrl;
}
