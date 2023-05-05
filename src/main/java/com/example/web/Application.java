package com.example.web;

import com.example.web.controller.LoginController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.web.mapper")
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        LoginController loginController = run.getBean("loginController", LoginController.class);
        System.out.println(loginController.getUserService());
    }

}
