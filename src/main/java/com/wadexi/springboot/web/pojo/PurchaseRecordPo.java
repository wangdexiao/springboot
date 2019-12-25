package com.wadexi.springboot.web.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.sql.Timestamp;
@Alias("purchaseRecord")
@Data
public class PurchaseRecordPo implements Serializable {

    private Long id;
    private Long userId;
    private Long productId;
    private double price;
    private int quantity;//数量
    private double sum;//总价
    private Timestamp timestamp;
    private String note;

}
