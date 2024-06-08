package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.NotificationDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Notification", description = "REST API for Notification information")
@RequestMapping("/v1/notification")
public interface NotificationApi {
    @GetMapping(value = "/getAll")
    List<NotificationDto> getAllNotification();
}
