package com.usman.forum.service;


import com.usman.forum.model.Role;

public interface RoleService {

    void saveRole(Role rol);

    void asignRole(Long userId, Long roleId);
}
