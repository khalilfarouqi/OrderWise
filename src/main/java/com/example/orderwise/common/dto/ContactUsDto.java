package com.example.orderwise.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ContactUsDto implements Serializable {
    private Long id;
    private Date dateEnvoy;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String message;
    private Boolean isRead;
}
