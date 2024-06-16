package com.example.orderwise.repository;

import com.example.orderwise.entity.MyMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyMoneyRepository extends JpaRepository<MyMoney, Long> {
    List<MyMoney> findByUserUsername(String username);
}
