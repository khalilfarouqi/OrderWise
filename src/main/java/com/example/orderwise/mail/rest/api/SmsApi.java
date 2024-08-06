package com.example.orderwise.mail.rest.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@Tag(name = "Sms", description = "REST API for Sms information")
@RequestMapping("/v1/sms")
public interface SmsApi {
    @PostMapping("/send")
    ResponseEntity<String> sendSms(@RequestParam String to, @RequestParam String text);
    @PostMapping("/whatsapp")
    ResponseEntity<String> sendWhatsAppMessage(@RequestParam String to, @RequestParam String message);
}
