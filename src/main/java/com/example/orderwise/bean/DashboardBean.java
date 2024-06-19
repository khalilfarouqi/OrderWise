package com.example.orderwise.bean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class DashboardBean {
    private double totalSales;
    private int currentMonthOrderCount;
    private int currentDayOrderCount;
    private int ordersRejectedInConfirmation;
    private int ordersRejectedInDelivery;
    private int ordersInProgress;
    private int ordersNotTreated;
    private int ordersReturned;
    private int ordersValidated;
    private int ordersToConfirm;
    private int dateOrdersToConfirm;
    private int ordersToDeliver;
    private int dateOrdersToDeliver;
    private int ordersToReturn;
    private int dateOrdersToReturn;
}
