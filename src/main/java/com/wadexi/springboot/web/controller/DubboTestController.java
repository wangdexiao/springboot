package com.wadexi.springboot.web.controller;

import com.dubbo.provider.bean.Order;
import com.wadexi.springboot.dubbo.OrderServiceConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DubboTestController {


    final static Logger logger = LoggerFactory.getLogger(PurchaseController.class);
    @Autowired
    private OrderServiceConsumer orderServiceConsumer;

    @RequestMapping("/dubbotest")
    public void dubboTest(){
        Order order = orderServiceConsumer.getOrder("123456");
        logger.error("dubbo 服务调用成功：" + order.toString());
    }
}
