package com.example.orderwise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String object;

    private String body;

    private Boolean isRead;

    private Boolean notificationWeb;

    @ManyToOne
    private User user;

    @OneToOne
    @JoinColumn(name = "notification_group_id")
    private NotificationGroup notificationGroup;
}
