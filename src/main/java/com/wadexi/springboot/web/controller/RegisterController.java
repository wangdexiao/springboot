package com.wadexi.springboot.web.controller;

import com.dubbo.provider.api.UserService;
import com.dubbo.provider.pojo.RegisterEntity;
import com.wadexi.springboot.web.bean.Result;
import com.wadexi.springboot.web.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class RegisterController {

    @Resource
    private UserMapper userMapper;

    //远程dubbouser服务
    @Resource
    private UserService userService;


    @GetMapping("/registerPage")
    public String registerPage(){
        return "register";
    }


    @ResponseBody
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @PostMapping("/registerAccount")
    public Result<String> register(RegisterEntity registerEntity){
        //向账户表account添加记录
        userMapper.insert(registerEntity);
//        int i = 3 / 0;
        //调用远程dubbo服务
        boolean result = userService.initUser(registerEntity);
        if (!result){
            throw new RuntimeException("远程dubbo服务调用失败");
        }
        int i = 3 / 0;
        return Result.successedResult("200 ok");
    }
}
