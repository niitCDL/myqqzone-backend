package com.example.web.service;

import com.example.web.entity.User;

public interface UserService {
    User login(String username, String password);

    User registerUser(User user);

    boolean checkNickName(String nickName);
}
