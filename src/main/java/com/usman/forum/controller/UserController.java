package com.usman.forum.controller;

import com.usman.forum.dto.BaseResponseDto;
import com.usman.forum.dto.UserDto;
import com.usman.forum.mapper.UserMapper;
import com.usman.forum.service.UserService;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;
    private  final UserMapper mapper;


    @PostMapping("/")
    public ResponseEntity<BaseResponseDto> saveUser(@RequestBody  UserDto userDto){
        log.info("postman------------------------------------------------");
        log.info(userDto.toString());
        log.info(mapper.toEntity(userDto).toString());
        service.saveUser(mapper.toEntity(userDto));
        return ResponseEntity.ok(BaseResponseDto.builder().message("User saved successfully").build());}


    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDto> updateUser(@PathVariable("id") Long id,@RequestBody UserDto userDto){
        log.info(userDto.toString());
        service.updateUser(id,mapper.toEntity(userDto));
        return ResponseEntity.ok(BaseResponseDto.builder().message("User updated successfully").build());}





}
