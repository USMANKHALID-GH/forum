package com.usman.forum.service;

import com.usman.forum.model.User;

public interface UserService {
    void saveUser(User user);

    void updateUser(Long id, User user);
}
