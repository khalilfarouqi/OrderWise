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
}
