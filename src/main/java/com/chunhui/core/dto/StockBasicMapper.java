package com.chunhui.core.dto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface StockBasicMapper {

    @Select("select * from stock_fundamental")
    List<StockBasicDao> getAllBasicInfo();

    @Select("select * from stock_fundamental_per_day where date = #{someday}")
    List<StockBasicDao> getBasicInfoOnSomeday(String someday);


}
