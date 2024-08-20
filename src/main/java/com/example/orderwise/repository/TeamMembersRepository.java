package com.example.orderwise.repository;

import com.example.orderwise.entity.TeamMembers;
import com.example.orderwise.entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMembersRepository extends JpaRepository<TeamMembers, Long> {
    List<TeamMembers> findAllByUserTypeAndAvailabilityIsTrue(UserType userType);
}
