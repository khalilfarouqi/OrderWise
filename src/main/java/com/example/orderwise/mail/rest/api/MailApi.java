package com.example.orderwise.mail.rest.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@Tag(name = "Mail", description = "REST API for mail information")
@RequestMapping("/v1/mail")
public interface MailApi {
    @PostMapping("/send")
    ResponseEntity<String> sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text);
}
