package com.wadexi.springboot.dubbo;

import com.dubbo.provider.api.OrderService;
import com.dubbo.provider.pojo.OrderEntity;
import lombok.Data;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;


public class OrderServiceConsumer {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubboConsumer.xml");
        context.start();

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        OrderService orderService = (OrderService) context.getBean("orderService");
        OrderEntity world = orderService.getOrder("world");
        System.out.println(world.toString());
        new CountDownLatch(1).await();
    }

}
