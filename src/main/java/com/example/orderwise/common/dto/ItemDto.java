package com.example.orderwise.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDto implements Serializable {
    private Long id;
    private int qty;

    private ProductDto product;
    private CartDto cart;
}
