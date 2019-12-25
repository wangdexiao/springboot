package com.wadexi.springboot.web.controller;

import com.dubbo.provider.api.OrderService;
import com.dubbo.provider.pojo.OrderEntity;
import com.wadexi.springboot.dubbo.OrderServiceConsumer;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


@Controller
public class DubboTestController {


    final static Logger logger = LoggerFactory.getLogger(PurchaseController.class);

    @Reference
    private OrderService orderService;

    @RequestMapping("/dubbotest")
    public void dubboTest(){
        OrderEntity order = orderService.getOrder("123456");
        logger.error("dubbo 服务调用成功：" + order.toString());
    }
}
