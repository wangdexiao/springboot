package com.wadexi.springboot.beforeaop.impl;

import com.wadexi.springboot.beforeaop.HelloService;
import org.springframework.util.StringUtils;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        if(StringUtils.isEmpty(name)){
            throw new RuntimeException("name == null");
        }
        System.out.println("hello " + name);
        return name;
    }
}
