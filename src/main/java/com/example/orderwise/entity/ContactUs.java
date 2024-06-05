package com.example.orderwise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="contact_us")
public class ContactUs {
    @Id
    @GeneratedValue
    private Long id;
    private Date dateEnvoy;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String message;
    private Boolean isRead;
}
