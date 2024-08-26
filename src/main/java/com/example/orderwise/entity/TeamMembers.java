package com.example.orderwise.entity;

import com.example.orderwise.entity.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="TeamMembers")
public class TeamMembers {
    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private int currentLoad;
    private Boolean availability;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
