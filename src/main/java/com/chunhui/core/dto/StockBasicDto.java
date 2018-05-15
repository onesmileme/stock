package com.chunhui.core.dto;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class StockBasicDto implements Serializable{

    private  String code;
    private  String name;
    private  float  pe;

    private  double outstanding; //流通股本(亿)
    private  double total; //总股本(亿)
    private  double totalAssets; //总资产(万)
    private  double liquidAssets; //流动资产
    private  double fixedAssets; //固定资产
    private  double reserved; //公积金
    private  double reservedPerShare; //每股公积金
    private  double esp; //每股收益
    private  double bvps; //每股净资
    private  double pb; //市净率
    private  String timeToMarket; //上市日期
    private  double undp; //未分利润
    private  float perundp; //每股未分配
    private  float rev; //收入同比(%)
    private  float profit; //利润同比(%)
    private  float gpr; //毛利率(%)
    private float npr; //净利润率(%)
    private float holders; //股东人数
    private String date; //数据更新日期

}
/*
*  `code` varchar(16) NOT NULL,
  `pe` float DEFAULT NULL,
  `outstanding` double DEFAULT NULL,
  `totals` double DEFAULT NULL,
  `totalAssets` double DEFAULT NULL,
  `liquidAssets` double DEFAULT NULL,
  `fixedAssets` double DEFAULT NULL,
  `reserved` double DEFAULT NULL,
  `reservedPerShare` float DEFAULT NULL,
  `esp` float DEFAULT NULL,
  `bvps` float DEFAULT NULL,
  `pb` float DEFAULT NULL,
  `timeToMarket` varchar(64) DEFAULT NULL,
  `undp` double DEFAULT NULL,
  `perundp` float DEFAULT NULL,
  `rev` float DEFAULT NULL,
  `profit` float DEFAULT NULL,
  `gpr` float DEFAULT NULL,
  `npr` float DEFAULT NULL,
  `holders` float DEFAULT NULL,
  `date` date NOT NULL,
* */