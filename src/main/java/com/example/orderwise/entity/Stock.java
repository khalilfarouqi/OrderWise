package com.example.orderwise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Stocks")
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
