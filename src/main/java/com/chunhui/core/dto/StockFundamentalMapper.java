package com.chunhui.core.dto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StockFundamentalMapper {

    @Select("select * from stock_fundamental where pe > 0 order by ${oby} desc limit #{limit};")
    List<StockFundamentDTO> todayFundationDesc(@Param("oby") String oderby , @Param("limit") int limit);

    @Select("select * from stock_fundamental where pe > 0 order by ${oby} asc limit #{limit};")
    List<StockFundamentDTO> todayFundationAsc(@Param("oby") String oderby , @Param("limit") int limit);

}
