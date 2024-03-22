package com.example.orderwise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String productNom;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double weight;
    @ManyToOne
    private User seller;
}
