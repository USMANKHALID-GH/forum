package com.usman.forum.mapper;

import com.usman.forum.dto.RoleDto;

import com.usman.forum.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDto, Role> {
    Role toEntity(RoleDto dto);
    RoleDto toDto(Role entity);
}
