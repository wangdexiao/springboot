package com.wadexi.springboot.dubbo;

import com.dubbo.provider.api.OrderService;
import com.dubbo.provider.bean.Order;
import lombok.Data;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;


@Data
@Component
public class OrderServiceConsumer {

    @Reference
    private OrderService orderService;

    public Order getOrder(String name) {
        return orderService.getOrder(name);
    }

}
