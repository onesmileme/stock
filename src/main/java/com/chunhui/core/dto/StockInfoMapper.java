package com.chunhui.core.dto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StockInfoMapper {

    @Select("select * from stock_basic")
    List<InfoDao> getAllInfos();

    @Select("select * from stock_basic where code = ${stockId};")
    InfoDao getStockInfo(@Param("stockId") String stockId);
}
