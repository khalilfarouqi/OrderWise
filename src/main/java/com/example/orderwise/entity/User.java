package com.example.orderwise.entity;

import com.example.orderwise.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private Date lastCheckIn;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String cin;
    private String tel;
    private String image;
    @Enumerated(EnumType.STRING)
    private City city;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
