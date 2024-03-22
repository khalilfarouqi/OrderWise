package com.example.orderwise.common.dto;

import com.example.orderwise.entity.enums.City;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto implements Serializable {
    private Long id;
    private String completeNom;
    private String tel;
    private String address;
    private String email;
    private String note;
    private String remarque;
    private City city;

    @JsonIgnore
    private List<OrderDto> orderDtos;
}
