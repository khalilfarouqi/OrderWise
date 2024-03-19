package com.example.orderwise.entity;

import com.example.orderwise.entity.enums.*;

import java.util.Date;

public class User {
    private Long id;
    private Date lastCheckIn;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String cin;
    private String tel;
    private City city;
    private Gender gender;
    private String image;
    private Role role;
    private UserType userType;
}
