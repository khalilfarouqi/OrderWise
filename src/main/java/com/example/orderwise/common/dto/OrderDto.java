package com.example.orderwise.common.dto;

import com.example.orderwise.entity.enums.Stage;
import com.example.orderwise.entity.enums.Status;
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
public class OrderDto implements Serializable {
    private Long id;
    private double totalPrice;
    private Date orderDate;
    private Date confirmationDate;
    private Date deliveryDate;
    private Date deliveredDate;
    private Date returnDate;
    private Date returnHomeDate;
    private int trackingCode;
    private Stage stage;
    private Status status;

    private CustomerDto customer;
    private CartDto cart;

    @JsonIgnore
    private List<OrderAssignmentDto> orderAssignmentDtos;
}
