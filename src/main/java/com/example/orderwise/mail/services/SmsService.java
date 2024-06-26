package com.example.orderwise.mail.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SmsService {
    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    @Value("${infobip.apiKey}")
    private String apiKey;

    @Value("${infobip.apiUrl}")
    private String apiUrl;

    @Value("${infobip.senderId}")
    private String senderId;

    private final RestTemplate restTemplate;

    public SmsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendSms(String to, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "App " + apiKey);

        String body = String.format("{\"from\":\"%s\",\"to\":\"%s\",\"text\":\"%s\"}", senderId, to, message);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
            logger.info("SMS sent successfully: {}", response.getBody());
        } catch (Exception e) {
            logger.error("Failed to send SMS: {}", e.getMessage());
        }
    }
}
