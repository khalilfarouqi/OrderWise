package com.example.orderwise.common.dto;

import com.example.orderwise.entity.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDto {
    private Long id;
    private double itemsPrice;

    private CustomerDto customerDto;
    @JsonIgnore
    private List<ItemDto> itemDtos;
}
