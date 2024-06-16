package com.example.orderwise.common.dto;

import com.example.orderwise.entity.enums.City;
import com.example.orderwise.entity.enums.Gender;
import com.example.orderwise.entity.enums.Role;
import com.example.orderwise.entity.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {
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
    private City city;
    private Gender gender;
    private Role role;
    private UserType userType;

    @JsonIgnore
    private List<NotificationDto> notificationDtos;

    @JsonIgnore
    private List<MyMoneyDto> myMoneyDtos;
}
