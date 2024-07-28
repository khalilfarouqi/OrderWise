package com.example.orderwise.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigAppDto implements Serializable {
    private Long id;
    private String email;
    private String tel;
    private String address;
    private String facebookUrl;
    private String instagramUrl;
    private String whatsappUrl;
    private String twitterUrl;
    private String linkedInUrl;
    private String youtubeUrl;

    private String avgDelivered;
    private String avgConfirmation;
}
