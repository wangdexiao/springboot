package com.wadexi.springboot.web.controller;

import com.wadexi.springboot.web.bean.Result;
import com.wadexi.springboot.web.service.PurchaseService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class PurchaseController {

    final static Logger logger = LoggerFactory.getLogger(PurchaseController.class);

    @Resource
    private PurchaseService purchaseService;


    @GetMapping("/hello")
    public String freemarkertest(Model model){
        logger.trace("freemarkertest.............");
        logger.debug("freemarkertest.............");
        logger.info("freemarkertest.............");
        logger.warn("freemarkertest.............");
        logger.error("freemarkertest.............");
        model.addAttribute("name", "wadexi");
        return "hello";
    }


    @ResponseBody
    @PostMapping("/purchase")
    public Result purchase(Long userId, Long productId, Integer quantity){
        boolean success = purchaseService.purchase(userId, productId, quantity);
        String message = success ? "抢购成功" : "抢购失败";

        return Result.successedResult(message);
    }




}
