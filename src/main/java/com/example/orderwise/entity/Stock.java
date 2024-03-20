package com.example.orderwise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    private int quantity;
    private Date additionDate;
    private Date lastSaleDate;
    private Date lastModifiedDate;
    @ManyToOne
    private Product product;
}
