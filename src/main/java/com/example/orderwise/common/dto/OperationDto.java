package com.example.orderwise.common.dto;

import com.example.orderwise.entity.enums.OperationStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationDto implements Serializable {
    private Long id;
    private Date operationDate;
    private Double amount;
    private OperationStatus operationStatus;

    private UserDto seller;
}
