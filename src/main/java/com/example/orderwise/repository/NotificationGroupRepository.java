package com.example.orderwise.repository;

import com.example.orderwise.entity.NotificationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationGroupRepository extends JpaRepository<NotificationGroup, Long> {
}
