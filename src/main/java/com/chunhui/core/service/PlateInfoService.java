package com.chunhui.core.service;

import com.chunhui.core.dao.PlateInfoMapper;
import com.chunhui.core.dto.StockBasicDto;
import com.chunhui.core.dto.StockBasicDto;
import com.chunhui.core.dto.StockBasicMapper;
import com.chunhui.core.dto.StockIndustryBasicDTO;
import com.chunhui.stock.PlateController;
import org.apache.ibatis.annotations.Mapper;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
public class PlateInfoService {

    private  static Logger logger = LoggerFactory.getLogger(PlateInfoService.class);

    private static final String TAG = "PlateService";

    @Autowired
    PlateInfoMapper plateInfoMapper;

    @Autowired
    StockBasicMapper basicMapper;

    public LinkedList<StockBasicDto> getIndustryStockInfo(String industryName){

        return getIndustryStockInfo(industryName,true);
    }

    public LinkedList<StockBasicDto> getIndustryStockInfo(String industryName , boolean sortByPe ){

        List<StockIndustryBasicDTO> industryStocks = plateInfoMapper.stocksOfIndustry(industryName);

        LinkedList<StockBasicDto> stockInfos = new LinkedList<StockBasicDto>();

        for (StockIndustryBasicDTO dto : industryStocks) {
            //System.out.println("industry: "+dto.getIname()+" stock id: "+dto.getScode()+" name: "+dto.getSname());
            try {
                StockBasicDto stockBasicDto = basicMapper.getBasicInfoOfStock(dto.getScode());
                stockBasicDto.setName(dto.getSname());
                stockInfos.add(stockBasicDto);
            }catch (Exception e){
                //股票退市
                //System.err.println("get stock failed stockId "+dto.getScode()+" name: "+dto.getSname()+" industry name:"+dto.getIname());
            }
        }

        try {
            stockInfos.sort(new Comparator<StockBasicDto>() {
                @Override
                public int compare(StockBasicDto o1, StockBasicDto o2) {
                    if (o1.getPe() == o2.getPe()) {
                        return 0;
                    }
                    return o1.getPe() < o2.getPe() ? -1 : 1;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            logger.error("sort failed "+e.getLocalizedMessage());
        }

        return stockInfos;
    }


    public List<String> industyList(){

        if (plateInfoMapper == null){

            System.out.println("plateinfo manager is null");
            return null;
        }
        try {

            return plateInfoMapper.getIndustryList();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }

        return null;
    }
}
