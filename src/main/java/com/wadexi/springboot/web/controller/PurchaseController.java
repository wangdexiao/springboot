package com.wadexi.springboot.web.controller;

import com.wadexi.springboot.web.service.PurchaseService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;


    @GetMapping("/hello")
    public String freemarkertest(Model model){
        model.addAttribute("name", "wadexi");
        return "hello";
    }


    @ResponseBody
    @PostMapping("/purchase")
    public  Result purchase(Long userId,Long productId,Integer quantity){
        boolean success = purchaseService.purchase(userId, productId, quantity);
        String message = success ? "抢购成功" : "抢购失败";
        Result result = new Result(success, message);
        return result;
    }


    @Data
    class Result{
        private boolean success = false;
        private String message;

        public Result(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
    }

}
