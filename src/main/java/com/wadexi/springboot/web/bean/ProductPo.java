package com.wadexi.springboot.web.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("product")
@Data
public class ProductPo implements Serializable {

    private static final long serialVersionUID = -1;

    private Long id;
    private String productName;
    private int stock;//库存
    private double price;
    private int version;
    private String note;

}
