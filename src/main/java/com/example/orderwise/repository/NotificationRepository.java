package com.example.orderwise.repository;

import com.example.orderwise.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByIsReadIsFalseAndId(Long id);

    @Query("select n from Notification n inner join User u on n.user.id = u.id where u.username = :username and n.isRead is false")
    List<Notification> getNotificationNotReadByUsername(@Param("username") String username);
}
