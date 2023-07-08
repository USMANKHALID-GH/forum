//package com.usman.forum.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.usman.forum.config.JwtUtil;
//import com.usman.forum.dto.AuthenticationResponse;
//import com.usman.forum.dto.UserDto;
//import com.usman.forum.mapper.AuthenticateMapper;
//import com.usman.forum.mapper.UserMapper;
//import com.usman.forum.model.Role;
//import com.usman.forum.model.User;
//import com.usman.forum.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//    private final static  String END_POINT="/api/user";
//
//    @Autowired private MockMvc mockMvc;
//
//    @Autowired private ObjectMapper objectMapper;
//
//    @MockBean private UserService service;
//
//    @MockBean private JwtUtil jwtUtil;
//    @Autowired
//    @MockBean private UserMapper mapper;
//
//    @MockBean private AuthenticateMapper authenticateMapper;
//
//    @Test
//    void register() throws Exception {
//        List<Role> roleList= List.of(new Role(1l,"USER"), new Role(1l, "ADMIN"));
//
//
//
//        UserDto userDto = new UserDto(
//                "uaman",
//                "khalid",
//                "java","loftyusman@444.com",
//                "123","1222" ,roleList
//        );
//
//        System.out.println(userDto.toString());
//        User user=mapper.toEntity(userDto);
//        System.out.println(user);
//
//        AuthenticationResponse authenticationResponse=service.register(user);
//        System.out.println(authenticationResponse);
//         String web= objectMapper.writeValueAsString(service.register(mapper.toEntity(userDto)));
//
//        System.out.println("\n"+web+"this is the response");
//         mockMvc.perform(post(END_POINT+"/register").contentType("application/json")
//                 .content(web)).andExpect(status().isAccepted())
//                 .andDo(print());
//
//    }
//
//    @Test
//    void just(){
//        assertThat(4).isEqualTo(4);
//    }
//
//
//
//}