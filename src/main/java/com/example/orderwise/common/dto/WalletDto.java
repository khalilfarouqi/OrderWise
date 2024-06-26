package com.example.orderwise.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletDto implements Serializable {
    private Long id;
    private double sold;
    private double amountCredited;
    private double amountDeposited;

    private UserDto user;
    private UserDto seller;
}
