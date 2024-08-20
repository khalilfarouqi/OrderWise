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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
