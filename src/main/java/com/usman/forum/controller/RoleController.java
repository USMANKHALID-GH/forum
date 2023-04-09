package com.usman.forum.controller;

import com.usman.forum.dto.BaseResponseDto;
import com.usman.forum.dto.RoleDto;
import com.usman.forum.dto.UserDto;
import com.usman.forum.mapper.RoleMapper;
import com.usman.forum.model.Role;
import com.usman.forum.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService service;
    private final RoleMapper mapper;

    @PostMapping("/")
    public ResponseEntity<BaseResponseDto> saveRole(@RequestBody RoleDto roleDto){
          service.saveRole(mapper.toEntity(roleDto));
        return ResponseEntity.ok(BaseResponseDto.builder().message("User saved successfully").build());}

}
