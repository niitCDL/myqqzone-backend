package com.example.web.controller;

import com.example.web.entity.User;
import com.example.web.service.UserService;
import com.example.web.utils.ResponseUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RegisterController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseUtils register(User registeredUser){
        ResponseUtils responseUtils = new ResponseUtils();
        try {
            User user = userService.registerUser(registeredUser);
            responseUtils.setCode("200");
            responseUtils.getData().put("user",user);
        } catch (Exception e) {
            e.printStackTrace();
            responseUtils.setCode(e.getClass().getSimpleName());
        }
        return responseUtils;
    }

    @GetMapping("/checkNickName")
    @ResponseBody
    public ResponseUtils checkNickName(String nickName){
        ResponseUtils responseUtils = new ResponseUtils();
        boolean b = userService.checkNickName(nickName);
        responseUtils.getData().put("status",b);
        return responseUtils;
    }

}
