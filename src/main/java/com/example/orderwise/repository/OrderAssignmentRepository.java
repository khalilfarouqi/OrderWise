package com.example.orderwise.repository;

import com.example.orderwise.entity.OrderAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderAssignmentRepository extends JpaRepository<OrderAssignment, Long> {
    List<OrderAssignment> getOrderAssignmentsBySellerUsername(String username);
}
