package com.example.MediNest.controller;

import com.example.MediNest.model.RoleModel;
import com.example.MediNest.service.RoleService;
import com.example.MediNest.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    private final JwtUtil jwtUtil;

    @GetMapping("/getAllRoles")
    public ResponseEntity<List<RoleModel>> getAllRoles(@RequestHeader("Authorization") String tokenHeader){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(roleService.getAllRoles());
    }

}
