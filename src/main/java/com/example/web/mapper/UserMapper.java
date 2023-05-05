package com.example.web.mapper;


import com.example.web.entity.User;

public interface UserMapper {
    User selectByUserName(String username);
    User selectByNickName(String nickname);

    int insert(User user);
}
