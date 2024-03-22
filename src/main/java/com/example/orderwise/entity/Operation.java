package com.example.orderwise.entity;

import com.example.orderwise.entity.enums.OperationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Operations")
public class Operation {
    @Id
    @GeneratedValue
    private Long id;
    private Date operationDate;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private OperationStatus operationStatus;
    @ManyToOne
    private User seller;
}
