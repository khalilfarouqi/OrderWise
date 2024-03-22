package com.example.orderwise.repository;

import com.example.orderwise.entity.Destinations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationsRepository extends JpaRepository<Destinations, Long> {
}
