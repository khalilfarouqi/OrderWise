package com.example.orderwise.bean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class NotificationRequestBean {
    private String message;
    private String tel;
    private String toEmail;
    private String subject;
    private Map<String, Object> model;
    private String template;
}
