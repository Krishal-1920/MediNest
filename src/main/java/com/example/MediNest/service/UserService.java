package com.example.MediNest.service;

import com.example.MediNest.entity.Role;
import com.example.MediNest.entity.User;
import com.example.MediNest.entity.UserRole;
import com.example.MediNest.mapper.RoleMapper;
import com.example.MediNest.mapper.UserMapper;
import com.example.MediNest.model.RoleModel;
import com.example.MediNest.model.UserModel;
import com.example.MediNest.repository.RoleRepository;
import com.example.MediNest.repository.UserRepository;
import com.example.MediNest.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final RoleMapper roleMapper;


    public UserModel signUp(UserModel userModel) {

        User adduser = userMapper.userModelToUser(userModel);

        // Save User To get Id
        userRepository.save(adduser);

        // Extract Roles From Model
        List<String> rolesFromModel = userModel.getRoles().stream().map(r -> r.getRoleId()).toList();

        // Find Roles in DB
        List<Role> roleInDb = roleRepository.findAllByRoleIdIn(rolesFromModel);

        // Check If Roles are Valid
        List<String> rolesIdsInDb = roleInDb.stream().map(r -> r.getRoleId()).toList();

        List<String> invalidRoles = new ArrayList<>();

        // Checking for invalid Roles
        for (String roleId : rolesFromModel) {
            if (!rolesIdsInDb.contains(roleId)) {
                invalidRoles.add(roleId.toString());
            }
        }

        if (!invalidRoles.isEmpty()) {
            throw new RuntimeException("Invalid roles: " + invalidRoles);
        }

        // Filter Valid Roles
        List<Role> saveRoles = roleInDb.stream().filter(r -> rolesFromModel.contains(r.getRoleId())).toList();

        // Save User Roles
        for (Role role : saveRoles) {
            UserRole userRole = new UserRole();
            userRole.setUser(adduser);
            userRole.setRole(role);
            userRoleRepository.save(userRole);
        }

        // Return User
        UserModel userModelToReturn = userMapper.userToUserModel(adduser);
        List<UserRole> byUserUserId = userRoleRepository.findByUserUserId(adduser.getUserId());
        List<RoleModel> roleList =new ArrayList<>();
        byUserUserId.forEach(ur -> roleList.add(roleMapper.roleToRoleModel(ur.getRole())));
        userModelToReturn.setRoles(roleList);
        return userModelToReturn;

    }


    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}