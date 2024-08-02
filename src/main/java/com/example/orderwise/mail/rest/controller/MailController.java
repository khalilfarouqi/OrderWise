package com.example.orderwise.mail.rest.controller;

import com.example.orderwise.mail.rest.api.MailApi;
import com.example.orderwise.mail.services.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MailController implements MailApi {
    private final MailService mailService;

    @Override
    public ResponseEntity<String> sendEmail(String to, String subject, String text) {
        return mailService.sendSimpleEmail(to, subject, text);
    }
}
