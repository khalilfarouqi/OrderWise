package com.example.orderwise.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardBean {
    private double totalSales;
    private int currentMonthOrderCount;
    private int currentDayOrderCount;
    private int ordersRejectedInConfirmation;
    private int ordersRejectedInDelivery;
    private int ordersInProgress;
    private int ordersReturned;
    private int ordersValidated;
    private int ordersToConfirm;
    private int ordersToDeliver;
    private int ordersToReturn;
}
