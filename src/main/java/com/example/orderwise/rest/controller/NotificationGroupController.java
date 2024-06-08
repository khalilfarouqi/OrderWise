package com.example.orderwise.rest.controller;

import com.example.orderwise.rest.api.NotificationGroupApi;
import com.example.orderwise.service.NotificationGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class NotificationGroupController implements NotificationGroupApi {
    private final NotificationGroupService notificationGroupService;
}
