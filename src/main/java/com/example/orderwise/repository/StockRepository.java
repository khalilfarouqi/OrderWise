package com.example.orderwise.repository;

import com.example.orderwise.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> getStocksByProductSellerUsername(String username);
    @Query("SELECT s FROM Stock s ORDER BY s.additionDate desc")
    List<Stock> findAllOrderedByAdditionDate();
}
