package com.chunhui.core.dto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StockInfoMapper {

    @Select("select * from info")
    List<InfoDao> getAllInfos();
}
