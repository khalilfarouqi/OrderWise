package com.example.orderwise.repository;

import com.example.orderwise.entity.MyMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyMoneyRepository extends JpaRepository<MyMoney, Long> {
    Optional<MyMoney> findByUserUsername(String username);
}
