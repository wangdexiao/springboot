package com.wadexi.springboot.beforeaop;

import com.wadexi.springboot.beforeaop.impl.HelloServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();

        HelloService helloServiceProxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        System.out.println(helloServiceProxy.sayHello("test"));
    }


}
