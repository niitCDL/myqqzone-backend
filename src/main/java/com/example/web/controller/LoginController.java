package com.example.web.controller;

import com.example.web.entity.User;
import com.example.web.service.UserService;
import com.example.web.utils.ResponseUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class LoginController {


    @Resource
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseUtils login(String username, String password) {
        ResponseUtils resp = new ResponseUtils();
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            User user = userService.login(username, password);
            user.setPassword(null);
            user.setSalt(null);
            resp.setCode("200");
            map.put("user", user);
            resp.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

}
