package com.example.orderwise.common.dto;

import com.example.orderwise.entity.enums.UserType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamMembersDto implements Serializable {
    private Long id;
    private String fullName;
    private UserType userType;
    private int currentLoad;
    private Boolean availability;

    private UserDto user;
}
