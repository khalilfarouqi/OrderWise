package com.example.orderwise.entity;

import com.example.orderwise.entity.enums.City;
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
@Table(name="Customers")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String completeNom;
    private String tel;
    private String address;
    private String email;
    private String note;
    private String remarque;
    @Enumerated(EnumType.STRING)
    private City city;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Order> orders;
}
