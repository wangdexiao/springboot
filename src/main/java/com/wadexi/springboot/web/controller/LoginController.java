package com.wadexi.springboot.web.controller;

import com.wadexi.springboot.web.bean.Result;
import com.wadexi.springboot.web.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @ResponseBody
    @PostMapping("/userlogin")
    public Result<String> login(User user){
        if(user != null && "root".equals(user.getUser()) && "root".equals(user.getPasswd()))
            return Result.successedResult("登录成功");
        else
            return Result.failResult("登录失败");
    }
}
