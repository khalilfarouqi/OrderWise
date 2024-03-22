package com.example.orderwise.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto implements Serializable {
    private Long id;
    private String productNom;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double weight;

    private UserDto seller;
}
