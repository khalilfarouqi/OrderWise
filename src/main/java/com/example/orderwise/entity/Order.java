package com.example.orderwise.entity;

import com.example.orderwise.entity.enums.Stage;
import com.example.orderwise.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private double totalPrice;
    private Date orderDate;
    private Date confirmationDate;
    private Date deliveryDate;
    private Date deliveredDate;
    private Date returnDate;
    private Date returnHomeDate;
    private int trackingCode;
    private Date noAnswerDate;
    private String holdTo;
    private String noAnswerBy;
    private String confirmationBy;
    private String deliveredBy;
    private String returnedBy;
    @Enumerated(EnumType.STRING)
    private Stage stage;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Cart cart;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<OrderAssignment> orderAssignments;
}
