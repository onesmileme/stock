package com.chunhui.core.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class StockFundamentDTO {

    private String code;
    private double pe;
    private double outstanding;
    private double totals;
    private double totalAssets;
    private double liquidAssets;
    private double fixedAssets;
    private double reserved;
    private double reservedPerShare;
    private double esp;
    private double bvps;
    private double pb;
    private String timeToMarket;
    private double undp;
    private double perundp;
    private double rev;
    private double profit;
    private double gpr;
    private double npr;
    private double holders;
    private Date date;

}
