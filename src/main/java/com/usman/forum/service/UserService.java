package com.usman.forum.service;

import com.usman.forum.dto.UserDto;
import com.usman.forum.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    void saveUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    User findUser(Long id);

    Page<User> findAllUser(Pageable pageable);
}
