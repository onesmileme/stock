package com.chunhui.stock;

import com.chunhui.core.dto.StockBasicDto;
import com.chunhui.core.dto.StockBasicMapper;
import com.chunhui.core.dto.StockFundamentDTO;
import com.chunhui.core.dto.StockFundamentalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("stockinfo")
public class FundamentalInfoController {

    @Autowired
    StockBasicMapper basicMapper;

    @Autowired
    StockFundamentalMapper fundamentalMapper;

    @RequestMapping("history")
    public String getHistoryInfo(@RequestParam("stockId") String stockId){

        if (stockId == null || stockId.length() == 0){
            return "";
        }

        StringBuffer info = new StringBuffer();

        info.append("<div>");
        info.append("<table border=\"1\"><thead>"+
                "<th>code</th>"+
                "<th>pe</th>"+
                "<th>流通股本(亿)</th>"+
                "<th>总股本(亿)</th>"+
                "<th>总资产(万)</th>"+
                "<th>流动资产</th>"+
                "<th>固定资产</th>"+
                "<th>公积金</th>"+
                "<th>每股公积金</th>"+
                "<th>每股收益</th>"+
                "<th>每股净资</th>"+
                "<th>市净率</th>"+
                "<th>上市日期</th>"+
                "<th>未分利润</th>"+
                "<th>每股未分配</th>"+
                "<th>收入同比(%)</th>"+
                "<th>利润同比(%)</th>"+
                "<th>毛利率(%)</th>"+
                "<th>净利润率(%)</th>"+
                "<th>股东人数</th>"+
                "<th>数据更新日期</th>"+
                "</thead>");
        info.append("<tbody>");
        List<StockBasicDto> stocks = basicMapper.getBasicInfoOfStockByDays(stockId);
        for (StockBasicDto dao : stocks){

           String row = "<tr>"+
                   "<td>"+dao.getCode()+"</td>"+
                   "<td>"+dao.getPe()+"</td>"+
                   "<td>"+dao.getOutstanding()+"</td>"+
                   "<td>"+dao.getTotal()+"</td>"+
                   "<td>"+dao.getTotalAssets()+"</td>"+
                   "<td>"+dao.getLiquidAssets()+"</td>"+
                   "<td>"+dao.getFixedAssets()+"</td>"+
                   "<td>"+dao.getReserved()+"</td>"+
                   "<td>"+dao.getReservedPerShare()+"</td>"+
                   "<td>"+dao.getEsp()+"</td>"+
                    "<td>"+dao.getBvps()+"</td>"+
                    "<td>"+dao.getPb()+"</td>"+
                    "<td>"+dao.getTimeToMarket()+"</td>"+
                    "<td>"+dao.getUndp()+"</td>"+
                    "<td>"+dao.getPerundp()+"</td>"+
                    "<td>"+dao.getRev()+"</td>"+
                    "<td>"+dao.getProfit()+"</td>"+
                    "<td>"+dao.getGpr()+"</td>"+
                    "<td>"+dao.getNpr()+"</td>"+
                    "<td>"+dao.getHolders()+"</td>"+
                    "<td>"+dao.getDate()+"</td>"+
                   "</tr>";

           info.append(row);

        }
        info.append("</tbody>");

        info.append("</table>");
        info.append("</div>");

        return info.toString();
    }

    
    public String getListByPe(String date){
        return "Unsupported";
    }

    @RequestMapping(value = "fundamental")
    public String getFundamental(@RequestParam(name = "orderby" , required = false) String orderby ,
                                 @RequestParam(name = "asc", required = false , defaultValue = "1") String asc ,
                                 @RequestParam(name = "limit" , required = false , defaultValue = "100") int limit){

        if (orderby == null || orderby.length() == 0){
            orderby = "pe";
        }
        /*
        if (asc == "1"){
            orderby += " asc";
        }else{
            orderby += " desc";
        }
        */
        List<StockFundamentDTO> fundamentDTOS = null;
        if (asc.equalsIgnoreCase("1")){
            fundamentDTOS = fundamentalMapper.todayFundationAsc(orderby,limit);
        }else {
           fundamentDTOS = fundamentalMapper.todayFundationDesc(orderby,limit);
        }

        StringBuffer info = new StringBuffer();
        info.append("<div>");
        info.append("<table border=\"1\"><thead>"+
                "<th>code</th>"+
                "<th>pe</th>+"+
                "<th>outstanding</th>+"+
                "<th>totals</th>+"+
                "<th>totalAssets</th>+"+
                "<th>liquidAssets</th>+"+
                "<th>fixedAssets</th>+"+
                "<th>reserved</th>+"+
                "<th>reservedPerShare</th>+"+
                "<th>esp</th>+"+
                "<th>bvps</th>+"+
                "<th>pb</th>+"+
                "<th>timeToMarket</th>+"+
                "<th>undp</th>+"+
                "<th>perundp</th>+"+
                "<th>rev</th>+"+
                "<th>profit</th>+"+
                "<th>gpr</th>+"+
                "<th>npr</th>+"+
                "<th>holders</th>+"+
                "<th>date</th>+"+
                "</thead>");
        info.append("<tbody>");

        info.append("<tbody>");
        for (StockFundamentDTO dto : fundamentDTOS){

            System.out.println("code: "+dto.getCode()+" pe: "+dto.getPe());
            String row = "<tr>"+
                    "<td>"+ dto.getCode()+"</td>+"+
                    "<td>"+ dto.getPe()+"</td>+"+
                    "<td>"+ dto.getOutstanding()+"</td>+"+
                    "<td>"+ dto.getTotals()+"</td>+"+
                    "<td>"+ dto.getTotalAssets()+"</td>+"+
                    "<td>"+ dto.getLiquidAssets()+"</td>+"+
                    "<td>"+ dto.getFixedAssets()+"</td>+"+
                    "<td>"+ dto.getReserved()+"</td>+"+
                    "<td>"+ dto.getReservedPerShare()+"</td>+"+
                    "<td>"+ dto.getEsp()+"</td>+"+
                    "<td>"+ dto.getBvps()+"</td>+"+
                    "<td>"+ dto.getPb()+"</td>+"+
                    "<td>"+ dto.getTimeToMarket()+"</td>+"+
                    "<td>"+ dto.getUndp()+"</td>+"+
                    "<td>"+ dto.getPerundp()+"</td>+"+
                    "<td>"+ dto.getRev()+"</td>+"+
                    "<td>"+ dto.getProfit()+"</td>+"+
                    "<td>"+ dto.getGpr()+"</td>+"+
                    "<td>"+ dto.getNpr()+"</td>+"+
                    "<td>"+ dto.getHolders()+"</td>+"+
                    "<td>"+ dto.getDate()+"</td>+"+
                    "</tr>";
            info.append(row);
        }
        info.append("</tbody>");

        info.append("</table>");
        info.append("</div>");

        return info.toString();
    }

}

/*
*     private  double outstanding; //流通股本(亿)
    private  double total; //总股本(亿)
    private  double totalAssets; //总资产(万)
    private  double liquidAssets; //流动资产
    private  double fixedAssets; //固定资产
    private  double reserved; //公积金
    private  double reservedPerShare;//每股公积金
    private  double esp; //每股收益
    private  double bvps;//每股净资
    private  double pb;//市净率
    private  String timeToMarket;//上市日期
    private  double undp;//未分利润
    private  float perundp;//每股未分配
    private  float rev;//收入同比(%)
    private  float profit;//利润同比(%)
    private  float gpr;//毛利率(%)
    private float npr;//净利润率(%)
    private float holders;//股东人数
    private String date;//数据更新日期
* */