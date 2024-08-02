package com.example.orderwise.mail.rest.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@Tag(name = "Sms", description = "REST API for Sms information")
@RequestMapping("/v1/sms")
public interface SmsApi {
}
