package com.xing.controller;

import com.xing.protobuflearn.entity.Login;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequestMapping("/index")
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index() {
        return "hello world";
    }

    @RequestMapping("login")
    public Login.LoginResponse login(HttpServletRequest request) {
        System.out.println("---------------start login---------------------");
        Login.LoginResponse.Builder builder = Login.LoginResponse.newBuilder();

        try {
            request.setCharacterEncoding("utf-8");
            Login.LoginRequest loginData = Login.LoginRequest.parseFrom(request.getInputStream());

            if("xing".equals(loginData.getUsername()) && "123".equals(loginData.getPassword())) {
                builder.setCode(200).setMsg("login success");
            }else{
                builder.setCode(-200).setMsg("login failed");
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Login.LoginResponse data =builder.build();
        System.out.println("data:" + data);

        return data;
    }


}
