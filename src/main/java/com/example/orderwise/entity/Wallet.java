package com.example.orderwise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Wallet {
    @Id
    @GeneratedValue
    private Long id;
    private double montant;
    @ManyToOne
    private User user;
    @ManyToOne
    private User seller;
}
