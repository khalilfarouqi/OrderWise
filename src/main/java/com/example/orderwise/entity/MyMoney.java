package com.example.orderwise.entity;

import com.example.orderwise.entity.enums.EtatDemande;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="MyMoney")
public class MyMoney {
    @Id
    @GeneratedValue
    private Long id;
    private Date dateDeDemande;
    private Double montant;
    @Enumerated(EnumType.STRING)
    private EtatDemande etatDemande;
    @ManyToOne
    private User user;
}
