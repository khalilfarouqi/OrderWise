package com.example.orderwise.common.dto;

import com.example.orderwise.entity.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationGroupDto implements Serializable {
    private Long id;
    private String object;
    private String body;
    private NotificationType notificationType;
    private Boolean notificationWeb;
    private Date dateEnvoy;
}
