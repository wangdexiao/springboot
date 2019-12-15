package com.wadexi.springboot.web.mapper;

import com.wadexi.springboot.web.bean.ProductPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductDao {

    //获取产品
    ProductPo getProduct(Long id);

    //减库存，
    int decreaseProduct(@Param("id") Long id, @Param("quantity") int quantity,@Param("version") int version);
}
