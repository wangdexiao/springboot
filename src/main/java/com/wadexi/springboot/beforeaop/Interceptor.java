package com.wadexi.springboot.beforeaop;


import org.apache.ibatis.plugin.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * 实现约定的流程
 */
public interface Interceptor {

    //事前方法
    boolean before();


    //事后方法
    void after();

    /**
     * 取代原有的事件方法
     * @param invocation
     * @return
     */
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;


    void afterRetrning();

    void afterThrowing();

    boolean useAround();


}
