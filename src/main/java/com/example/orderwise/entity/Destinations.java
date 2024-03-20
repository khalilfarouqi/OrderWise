package com.example.orderwise.entity;

import com.example.orderwise.entity.enums.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Destinations {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private City city;
    private double shippingCost;
    private double returnCost;
    private double refundCost;
    private int deliveryDays;

}
