package com.example.orderwise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Wallets")
public class Wallet {
    @Id
    @GeneratedValue
    private Long id;
    private double sold;
    @ManyToOne
    private User user;
    @ManyToOne
    private User seller;
}
