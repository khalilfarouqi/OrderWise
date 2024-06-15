package com.example.orderwise.repository;

import com.example.orderwise.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findBySellerUsername(String username);
    Optional<Wallet> findByUserUsername(String username);
}
