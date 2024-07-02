package com.example.orderwise.repository;

import com.example.orderwise.entity.User;
import com.example.orderwise.entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByCin(String cin);
    int countByUserType(UserType userType);
    int countByConfirmedBy(String confirmedBy);
    List<User> getAllByUserType(UserType userType);
}
