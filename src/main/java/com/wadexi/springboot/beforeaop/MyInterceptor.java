package com.wadexi.springboot.beforeaop;

import org.apache.ibatis.plugin.Invocation;

import java.lang.reflect.InvocationTargetException;

public class MyInterceptor implements Interceptor{

    @Override
    public boolean before() {
        System.out.println("before===========================");
        return false;
    }

    @Override
    public void after() {
        System.out.println("after=========================");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("around before===========================");
        Object proceed = invocation.proceed();
        System.out.println("around after===========================");
        return proceed;
    }

    @Override
    public void afterRetrning() {
        System.out.println("afterRetrning===========================");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing===========================");
    }

    @Override
    public boolean useAround() {
        return false;
    }
}
