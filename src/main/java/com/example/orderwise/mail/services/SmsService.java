package com.example.orderwise.mail.services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Service
public class SmsService {
    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    @Value("${infobip.apiKey}")
    private String apiKey;

    @Value("${infobip.apiUrl}")
    private String apiUrl;

    @Value("${infobip.baseUrl}")
    private String baseUrl;

    @Value("${infobip.baseApiUrl}")
    private String baseApiUrl;

    @Value("${infobip.senderId}")
    private String senderId;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Value("${twilio.whatsapp.number}")
    private String twilioWhatsappNumber;

    private final RestTemplate restTemplate;

    public SmsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> sendSms(String to, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "App " + apiKey);

        String body = String.format("{\"from\":\"OrderWise\",\"to\":\"%s\",\"text\":\"%s\"}", formatPhoneNumber(to), message);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
            return new ResponseEntity<>("SMS sent successfully: { " + response.getBody() + "}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send SMS: { " + e.getMessage() + "}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> sendWhatsAppMessage(String to, String message) {
        try {
            Message.creator(
                    new PhoneNumber("whatsapp:" + formatPhoneNumber(to)),
                    new PhoneNumber("whatsapp:" + twilioWhatsappNumber),
                    message).create();
            return new ResponseEntity<>("WhatsApp message sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send WhatsApp message : { " + e.getMessage() + "}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String formatPhoneNumber(String tel) {
        if (tel.startsWith("0")) {
            return tel.replaceFirst("0", "+212");
        }
        return tel;
    }
}
