package com.example.orderwise.repository;

import com.example.orderwise.entity.ConfigApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigAppRepository extends JpaRepository<ConfigApp, Long> {
}
