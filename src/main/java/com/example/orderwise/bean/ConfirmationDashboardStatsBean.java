package com.example.orderwise.bean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ConfirmationDashboardStatsBean {
    private int orderTreated;
    private int orderTreatedThisDay;
    private int orderTreatedThisMonth;
    private int accountToConfirmed;
    private int orderNotTreated;
    private int orderNotAnswerInConfirmation;
    private int orderRejectedInConfirmation;
    private int orderValidatedInConfirmation;
    private int ordersToConfirm;
    private int dateOrdersToConfirm;
    private int ordersToReturn;
    private int dateOrdersToReturn;
    private int accountToConfirm;
    private int dateAccountToConfirm;
}
