package com.example.MediNest.repository;

import com.example.MediNest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findAllByRoleIdIn(List<String> rolesFromModel);
}
