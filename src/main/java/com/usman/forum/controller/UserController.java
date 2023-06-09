package com.usman.forum.controller;

import com.usman.forum.dto.AuthenticationResponse;
import com.usman.forum.dto.BaseResponseDto;
import com.usman.forum.dto.UserAuthenticateRequestDto;
import com.usman.forum.dto.UserDto;
import com.usman.forum.mapper.AuthenticateMapper;
import com.usman.forum.mapper.UserMapper;
import com.usman.forum.model.User;
import com.usman.forum.service.UserService;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;



@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;
    private  final UserMapper mapper;
    private AuthenticateMapper authenticateMapper;



    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto request){
        User user=mapper.toEntity(request);
        log.info("\n,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        return  ResponseEntity.ok(service.register(user));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserAuthenticateRequestDto request){
        User user=authenticateMapper.toEntity(request);
        return  ResponseEntity.ok(service.authenticate(user));
    }

    @GetMapping("/")
    public  ResponseEntity<Page<UserDto>>  findAllUser(Pageable pageable){

        return  ResponseEntity.ok(new PageImpl<>( mapper.toDto(service.findAllUser(pageable).getContent())));

    }


    @GetMapping("/{id}")
    public  ResponseEntity<UserDto>  findUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(mapper.toDto(service.findUser(id)));
    }


    @PostMapping("/")
    public ResponseEntity<BaseResponseDto> saveUser(@RequestBody  UserDto userDto){

        service.saveUser(mapper.toEntity(userDto));
        return ResponseEntity.ok(BaseResponseDto.builder().message("User saved successfully").build());}


    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDto> updateUser(@PathVariable("id") Long id,@RequestBody UserDto userDto){
        log.info(userDto.toString());
        service.updateUser(id,mapper.toEntity(userDto));
        return ResponseEntity.ok(BaseResponseDto.builder().message("User updated successfully").build());}

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> unSelectbestAnswer(@PathVariable("id") Long id){

        service.deleteUser(id);
        return ResponseEntity.ok(BaseResponseDto.builder().message("user deleted successfully").build());

    }







}
