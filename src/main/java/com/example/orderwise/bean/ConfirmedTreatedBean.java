package com.example.orderwise.bean;

import com.example.orderwise.entity.enums.Stage;
import com.example.orderwise.entity.enums.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class ConfirmedTreatedBean {
    private int trackingCode;
    private Date treatedDate;
    private Stage stage;
    private Status status;
    private double totalPrice;
}
