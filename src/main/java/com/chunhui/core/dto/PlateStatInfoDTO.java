package com.chunhui.core.dto;

import lombok.Data;

@Data
public class PlateStatInfoDTO {

    String name;
    float averagePe;
    float averagePb;
    float minPe;
    float maxPe;
    String minPeCode;
    String minPeName;
    String maxPeCode;
    String maxPeName;

    float minPb;
    float maxPb;
    String minPbCode;
    String minPbName;
    String maxPbCode;
    String maxPbName;

}
