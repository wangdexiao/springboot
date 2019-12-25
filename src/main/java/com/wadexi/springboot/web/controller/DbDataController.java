package com.wadexi.springboot.web.controller;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.wadexi.springboot.web.pojo.ProductPo;
import com.wadexi.springboot.web.mapper.ProductDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
public class DbDataController {

    public Logger logger = LoggerFactory.getLogger(DbDataController.class);

    @Resource
    private ProductDao productDao;

    @ResponseBody
    @RequestMapping("/insertData")
    public void insertData(ProductPo productPo){
        for (int i = 0; i < 30000;i++){
            productDao.insert(productPo);
            logger.info("插入第" + i + "条数据");
        }

    }

    @ResponseBody
    @RequestMapping("/insertDatas")
    public void insertDatas(ProductPo productPo){

        for (int i = 0; i <1000 ; i++) {
            ArrayList arrayList = new ArrayList();
            for (int j = 0; j < 10000;j++){
                arrayList.add(productPo);
            }

            productDao.insertDatas(arrayList);
            logger.info("插入10000条成功");
        }



    }
}
