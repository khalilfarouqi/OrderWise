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
@Table(name="OrderAssignments")
public class OrderAssignment {
    @Id
    @GeneratedValue
    private Long id;
    private Date assignmentDate;
    @ManyToOne
    private Order order;
    @ManyToOne
    private User seller;
}
