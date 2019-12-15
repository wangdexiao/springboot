package com.wadexi.springboot.web.mapper;

import com.wadexi.springboot.web.bean.PurchaseRecordPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseProDao {

    int insertPurchaseRecordDao(PurchaseRecordPo pr);

}
