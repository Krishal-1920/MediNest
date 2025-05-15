package com.example.MediNest.service;

import com.example.MediNest.mapper.RoleMapper;
import com.example.MediNest.model.RoleModel;
import com.example.MediNest.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public List<RoleModel> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> roleMapper.roleToRoleModel(role)).toList();
    }

}
