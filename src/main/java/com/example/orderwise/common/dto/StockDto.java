package com.example.orderwise.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDto implements Serializable {
    private Long id;
    private int quantity;
    private Date additionDate;
    private Date lastSaleDate;
    private Date lastModifiedDate;

    private ProductDto product;
}
