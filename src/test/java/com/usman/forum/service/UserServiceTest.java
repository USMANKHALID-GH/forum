package com.usman.forum.service;

import com.usman.forum.config.JwtUtil;
import com.usman.forum.controller.UserController;
import com.usman.forum.dto.AuthenticationResponse;
import com.usman.forum.model.User;
import com.usman.forum.repository.UserRepository;
import com.usman.forum.service.Implementation.UserImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;

    private UserService userService;

    private AutoCloseable autoCloseable;

    @Mock
    private JwtUtil jwtUtil;
    private User user;

    @Mock
    private AuthenticationResponse authenticationResponse;


    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
        userService = new UserImp(userRepository, passwordEncoder, authenticationManager, jwtUtil);
        user = new User();
        user.setFirstName("uaman");
        user.setLastName("khalid");

        user.setRelatedField("java");
        user.setEmail("loftyusman@444.com");
        user.setPassword("123");
        user.setPhoneNumber("1222");

        userRepository.save(user);

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void register() {
        // Arrange
        User user = new User();
        user.setPassword("123");

        // Stubbing the password encoder behavior
        when(passwordEncoder.encode("123")).thenReturn("encodedPassword");

        // Stubbing the JWT util behavior
        when(jwtUtil.generateToken(user)).thenReturn("generatedToken");

        // Act
        AuthenticationResponse response = userService.register(user);

        // Assert
        verify(passwordEncoder).encode("123");
        verify(userRepository).save(user);
        verify(jwtUtil).generateToken(user);
        assertEquals("generatedToken", response.getToken());
    }

    @Test
    void authenticate() {
    }

    @Test
    void saveUser() {


        User user = new User();
        user.setPassword("123");

        // Act
        userService.saveUser(user);

        // Assert
        verify(passwordEncoder).encode("123");
        verify(userRepository).save(user);

    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void findUser() {
    }

    @Test
    void findAllUser() {
    }
}