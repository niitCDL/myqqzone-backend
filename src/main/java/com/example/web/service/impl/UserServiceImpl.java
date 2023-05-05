package com.example.web.service.impl;

import com.example.web.entity.User;
import com.example.web.mapper.UserMapper;
import com.example.web.service.UserService;
import com.example.web.service.exception.LoginException;
import com.example.web.service.exception.RegisterException;
import com.example.web.utils.Md5Utils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUserName(username);
        if (user == null) {
            throw new LoginException("用户名不存在");
        }
        //对password进行 md5 和 salt 加密 得到密文
        String md5Password = Md5Utils.md5Digest(password, user.getSalt());
        if (!md5Password.equals(user.getPassword())){
            throw new LoginException("密码错误");
        }
        return user;
    }

    @Override
    public User registerUser(User user) {
        User byNickName = userMapper.selectByNickName(user.getNickname());
        if (byNickName != null){
            throw new RegisterException("该昵称已存在,请更换一个!");
        }
        String username = "";
        String oldPassword = user.getPassword();
        while (true){
            for (int i = 0; i < 10; i++) {
                username += String.valueOf(1 + (int)(Math.random() * ((9 - 1) + 1)));
            }
            if (userMapper.selectByUserName(username)!=null){
                username = "";
                continue;
            }
            break;
        }
        user.setUsername(username);
        user.setSalt(new Random().nextInt(200));
        String md5Password = Md5Utils.md5Digest(user.getPassword(),user.getSalt());
        user.setPassword(md5Password);
        userMapper.insert(user);
        user.setPassword(oldPassword);
        System.out.println("注册成功");
        return user;
    }

    @Override
    public boolean checkNickName(String nickName) {
        return userMapper.selectByNickName(nickName) != null;
    }

    public static void main(String[] args) {


    }
}
