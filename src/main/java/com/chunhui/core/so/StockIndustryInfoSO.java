package com.chunhui.core.so;


import com.chunhui.core.dto.StockBasicDto;
import lombok.Data;

import java.util.List;

@Data
public class StockIndustryInfoSO {

    private String industryName;

    private float minPe;
    private float maxPe;
    private float midPe;
    private float avgPe;

    List<StockBasicDto> stocks;
}
