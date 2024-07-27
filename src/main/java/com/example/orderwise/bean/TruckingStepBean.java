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
public class TruckingStepBean {
    private Status status;
    private Stage stage;
    private Date dateStep;
}
