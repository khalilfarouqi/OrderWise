package com.example.orderwise.entity;

import com.example.orderwise.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private int quantity;
    private double totalPrice;
    private Date orderDate;
    private Date confirmationDate;
    private Date deliveryDate;
    private Date deliveredDate;
    private Date returnDate;
    private Date returnHomeDate;
    private int trackingCode;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Product product;
}
