package com.chunhui.core.dto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface StockBasicMapper {

    @Select("select * from stock_fundamental")
    List<StockBasicDto> getAllBasicInfo();

    @Select("select * from stock_fundamental_per_day where date = #{someday}")
    List<StockBasicDto> getBasicInfoOnSomeday(@Param("someday") String someday);

    @Select("select * from stock_fundamental_per_day where code = #{stockId}")
    List<StockBasicDto> getBasicInfoOfStockByDays(@Param("stockId") String stockId);

    @Select("select * from stock_fundamental where code = #{stockId}")
    StockBasicDto getBasicInfoOfStock(@Param("stockId") String stockId);

}
