package com.example.orderwise.rest.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@Tag(name = "Notification", description = "REST API for Notification information")
@RequestMapping("/v1/notification")
public interface NotificationApi {
}
