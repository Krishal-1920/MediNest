package com.example.MediNest.mapper;

import com.example.MediNest.entity.Role;
import com.example.MediNest.model.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleModel roleToRoleModel(Role role);
}
