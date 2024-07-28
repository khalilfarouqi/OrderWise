package com.example.orderwise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="ConfigApp")
public class ConfigApp {
    @Id
    @GeneratedValue
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
