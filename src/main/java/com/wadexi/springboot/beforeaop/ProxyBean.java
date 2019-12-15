package com.wadexi.springboot.beforeaop;

import org.apache.ibatis.plugin.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBean implements InvocationHandler {

    private Object target;
    private Interceptor interceptor;

    public static Object getProxyBean(Object target, Interceptor interceptor){
        ProxyBean proxyBean = new ProxyBean();
        proxyBean.target = target;
        proxyBean.interceptor = interceptor;
        Object proxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxyBean);
        return proxyInstance;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        /**
         * 将事先约定的流程织入
         */
        boolean exception = false;
        Invocation invocation = new Invocation(target, method, args);
        try {
            interceptor.before();
            if(interceptor.useAround()){
                interceptor.around(invocation);
            }else {
                return method.invoke(target, args);
            }
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
        } finally {
            try {
                interceptor.after();
                if(exception){
                    interceptor.afterThrowing();
                }else {
                    interceptor.afterRetrning();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }



        return null;
    }
}
