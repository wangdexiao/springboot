package com.wadexi.springboot.web.service.impl;

import com.wadexi.springboot.web.bean.ProductPo;
import com.wadexi.springboot.web.bean.PurchaseRecordPo;
import com.wadexi.springboot.web.mapper.ProductDao;
import com.wadexi.springboot.web.mapper.PurchaseProDao;
import com.wadexi.springboot.web.service.PurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private ProductDao productDao;

    @Resource
    private PurchaseProDao purchaseProDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean purchase(Long userId, Long productId, int quantity) {

        ProductPo product = productDao.getProduct(productId);

        if(product == null){
            return false;
        }

        int version = product.getVersion();//库存内存旧值

        if(product.getStock() < quantity){
            //库存不足
            return false;
        }

            //这里如果有其他线程提交事务 库存为0的情况也会超发

            //扣减库存
        int result = productDao.decreaseProduct(productId, quantity, version);

        if(result == 0){
            return false;
        }

        //初始化购买记录
            PurchaseRecordPo pr = this.initPurchaseRecord(userId, product, quantity);

            //插入购买记录
            purchaseProDao.insertPurchaseRecordDao(pr);
            return true;


    }

    private PurchaseRecordPo initPurchaseRecord(Long userId, ProductPo product, int quantity) {

        PurchaseRecordPo pr = new PurchaseRecordPo();
        pr.setUserId(userId);
        pr.setQuantity(quantity);
        pr.setProductId(product.getId());
        pr.setPrice(product.getPrice());
        pr.setSum(product.getPrice() * quantity);
        pr.setNote("购买日志 时间：" + System.currentTimeMillis());

        return pr;
    }
}
