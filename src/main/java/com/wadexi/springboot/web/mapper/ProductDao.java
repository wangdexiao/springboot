package com.wadexi.springboot.web.mapper;

import com.wadexi.springboot.web.pojo.ProductPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductDao {

    //插入产品
    int insert(ProductPo id);


    int insertDatas(@Param("productPoList") List<ProductPo> productPoList);

    //获取产品
    ProductPo getProduct(Long id);

    //减库存，
    int decreaseProduct(@Param("id") Long id, @Param("quantity") int quantity,@Param("version") int version);
}
