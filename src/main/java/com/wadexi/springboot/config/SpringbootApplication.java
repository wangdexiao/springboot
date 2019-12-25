package com.wadexi.springboot.config;

import com.wadexi.springboot.web.pojo.ProductPo;
import com.wadexi.springboot.web.mapper.ProductDao;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;

@Import(value = { DbConfig.class})
@SpringBootApplication(scanBasePackages = "com.wadexi.springboot.web")
@ImportResource("classpath:dubboConsumer.xml")
public class SpringbootApplication /*extends SpringBootServletInitializer*/ implements ApplicationContextAware {

    @Resource
    private ProductDao productDao;



    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        ProductPo product = productDao.getProduct(1L);
        System.out.println(product.toString());
}



}
