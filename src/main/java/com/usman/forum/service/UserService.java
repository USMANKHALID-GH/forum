package com.usman.forum.service;


import com.usman.forum.dto.AuthenticationResponse;
import com.usman.forum.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface UserService {
    AuthenticationResponse register(User user);


    AuthenticationResponse authenticate(User user);
    void saveUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    User findUser(Long id);

    Page<User> findAllUser(Pageable pageable);
}
