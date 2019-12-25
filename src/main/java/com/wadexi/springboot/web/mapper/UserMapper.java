package com.wadexi.springboot.web.mapper;

import com.dubbo.provider.pojo.RegisterEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insert(RegisterEntity  registerEntity);
}
