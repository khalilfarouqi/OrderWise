package com.example.orderwise.repository;

import com.example.orderwise.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByIsReadIsFalseAndId(Long id);
}
