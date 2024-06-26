package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.CustomerDto;
import com.example.orderwise.common.dto.NotificationDto;
import com.example.orderwise.mail.services.SmsService;
import com.example.orderwise.rest.api.NotificationApi;
import com.example.orderwise.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class NotificationController implements NotificationApi {
    private final NotificationService notificationService;
    private final SmsService smsService;

    @Override
    public List<NotificationDto> getAllNotification() {
        return notificationService.findAll();
    }

    public String sendSms(String to, String message) {
        try {
            smsService.sendSms(to, message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "SMS sent successfully!";
    }
    public List<NotificationDto> getAllNotificationNotRead(Long id) {
        return notificationService.getAllNotificationNotRead(id);
    }
}
