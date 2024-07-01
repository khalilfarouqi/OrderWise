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
    private String confirmationBy;
    private Date deliveryDate;
    private String deliveredBy;
    private Date deliveredDate;
    private Date returnDate;
    private String returnedBy;
    private Date returnHomeDate;
    private int trackingCode;
    private Stage stage;
    private Status status;

    private Date noAnswerDate;
    private String noAnswerBy;

    private CustomerDto customer;
    private CartDto cart;

    @JsonIgnore
    private List<OrderAssignmentDto> orderAssignmentDtos;
}
