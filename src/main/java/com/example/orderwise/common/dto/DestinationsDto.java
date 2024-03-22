package com.example.orderwise.common.dto;

import com.example.orderwise.entity.enums.City;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DestinationsDto {
    private Long id;
    private City city;
    private double shippingCost;
    private double returnCost;
    private double refundCost;
    private int deliveryDays;
}
