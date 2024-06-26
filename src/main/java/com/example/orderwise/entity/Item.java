package com.example.orderwise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Items")
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private int qty;
    @OneToOne
    private Product product;
    @ManyToOne
    @JsonIgnore
    private Cart cart;
}
