package com.example.orderwise.rest.controller;

import com.example.orderwise.rest.api.NotificationApi;
import com.example.orderwise.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class NotificationController implements NotificationApi {
    private final NotificationService notificationService;
}
