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
@Table(name="Carts")
public class Cart {
    @Id
    @GeneratedValue
    private Long id;
    private double itemsPrice;
    @OneToOne
    private Customer customer;
    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Item> items;
}
