package com.example.MediNest.repository;

import com.example.MediNest.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findByUserUserId(String userId);

    void deleteByRoleRoleIdInAndUserUserId(List<String> removeRoleIds, String userId);
}
