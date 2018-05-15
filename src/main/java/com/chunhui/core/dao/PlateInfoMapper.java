package com.chunhui.core.dao;

import com.chunhui.core.dto.StockIndustryBasicDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlateInfoMapper {

    @Select("select * from stock_industry_stocks where iname = #{iname};")
    List<StockIndustryBasicDTO> stocksOfIndustry(@Param("iname") String iname);

    @Select("select * from stock_industry_list;")
    List<String> getIndustryList();

}
