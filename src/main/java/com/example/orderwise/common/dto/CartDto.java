package com.example.orderwise.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDto implements Serializable {
    private Long id;
    private double itemsPrice;

    private CustomerDto customerDto;
    @JsonIgnore
    private List<ItemDto> itemDtos;
}
