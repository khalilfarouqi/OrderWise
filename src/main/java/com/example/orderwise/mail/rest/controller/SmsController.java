package com.example.orderwise.mail.rest.controller;

import com.example.orderwise.mail.rest.api.SmsApi;
import com.example.orderwise.mail.services.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SmsController implements SmsApi {
    private final SmsService smsService;

    @Override
    public ResponseEntity<String> sendSimpleSms(String to, String text) {
        return smsService.sendSimpleSms(to, text);
    }
}
