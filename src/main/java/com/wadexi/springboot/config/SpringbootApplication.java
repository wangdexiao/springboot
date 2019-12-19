package com.wadexi.springboot.config;

import com.wadexi.springboot.web.bean.ProductPo;
import com.wadexi.springboot.web.mapper.ProductDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.io.File;

@Import(Dbconfig.class)
@SpringBootApplication(scanBasePackages = "com.wadexi.springboot.web")
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

//    @Bean
//    public AbstractServletWebServerFactory embeddedServletContainerFactory() {
//
//        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
//        tomcatServletWebServerFactory.setDocumentRoot(
//                new File(rootDoc));
//        return  tomcatServletWebServerFactory;
//    }
}
