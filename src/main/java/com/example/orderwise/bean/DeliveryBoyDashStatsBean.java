package com.example.orderwise.bean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DeliveryBoyDashStatsBean {
    private double walletToday;
    private double moneyDepose;
    private double moneyPacket;
    private int orderTreatedToday;
    private int orderNoTreatedToday;
    private int orderNotTreated;
    private int orderLivrer;
    private int orderAnnuler;
    private int orderNotResponse;
    private int orderToTraite;
    private int dateOrderToTraite;
    private int orderToDeliver;
    private int dateOrderToDeliver;
    private int orderToReturn;
    private int dateOrderToReturn;
}
