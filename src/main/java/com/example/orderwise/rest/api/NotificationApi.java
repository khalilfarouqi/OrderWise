package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.NotificationDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@Tag(name = "Notification", description = "REST API for Notification information")
@RequestMapping("/v1/notification")
public interface NotificationApi {
    @GetMapping(value = "/getAll")
    List<NotificationDto> getAllNotification();
    @PostMapping("/send-sms")
    String sendSms(@RequestParam String to, @RequestParam String message);
    @PostMapping(value = "/readNotification/{id}")
    ResponseEntity<Map<String, String>> readNotification(@PathVariable("id") Long id);
    @GetMapping(value = "/getAllNotificationNotRead/{username}")
    List<NotificationDto> getAllNotificationNotRead(@PathVariable("username") String username);
}
