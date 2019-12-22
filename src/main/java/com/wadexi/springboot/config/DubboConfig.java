package com.wadexi.springboot.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo
@PropertySource("classpath:dubbo-consumer.properties")
//@ImportResource("classpath:dubbo-order-consumer.xml")
@ComponentScan("com.wadexi.springboot.dubbo")
public class DubboConfig {


}




