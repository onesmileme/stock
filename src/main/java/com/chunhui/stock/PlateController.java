package com.chunhui.stock;


import com.chunhui.core.dto.*;
import com.chunhui.core.service.PlateInfoService;
import com.chunhui.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("plate")
public class PlateController {


    private static  Logger logger = LoggerFactory.getLogger(PlateController.class);


    @Autowired
    PlateInfoService plateInfoService;


    @Autowired
    StockInfoMapper stockInfoMapper;


    @RequestMapping(value = "industrylist")
    public String industryList(){


        List<String> industryList =  plateInfoService.industyList();

        if (industryList == null ){
            return "NO DATA";
        }

        StringBuffer info = new StringBuffer();


        info.append("<div>");
        info.append("<table border=\"1\"><thead>"+
                "<th>行业名称</th></thread>");
        info.append("<tbody>");

        for (String name : industryList){
            info.append("<tr><td>"+name+"</td></tr>");
        }

        info.append("</tbody></table></div>");



        return info.toString();
    }


    /**
     * 获取行业的股票数据分析 包含市盈率 市净率
     * @param iname 行业名称
     * @return
     */
    @RequestMapping(value = "industrystockinfo")
    public String industryStocksInfo(@RequestParam(name = "iname" , required = true) String iname){

        List<PlateStatInfoDTO> plateStatInfoDTOList = new LinkedList<>();

        List<String> industryList = null;
        if (iname == null || iname.equalsIgnoreCase("all")){
            //get all data
            industryList =  plateInfoService.industyList();
        }else {
            industryList = new ArrayList<>(1);
            industryList.add(iname);

        }

        if (industryList == null || industryList.size() == 0){
            return "NO DATA";
        }

        for (String name :industryList ) {

            logger.info("handle "+name+" start");
            List<StockBasicDto> stockBasicDtos = plateInfoService.getIndustryStockInfo(name,false);
            if (stockBasicDtos == null || stockBasicDtos.size() == 0){
                logger.debug("no data for "+name);
                continue;
            }

            PlateStatInfoDTO plateStatInfoDTO = new PlateStatInfoDTO();
            plateStatInfoDTO.setName(name);

            //handle pe
            stockBasicDtos.sort(new Comparator<StockBasicDto>() {
                @Override

                public int compare(StockBasicDto o1, StockBasicDto o2) {

                    int o1pe = (int)(o1.getPe()*100);
                    int o2pe = (int)(o2.getPe()*100);

                    return o1pe - o2pe;

                    //return (int)(o1.getPe() - o2.getPe());
                }
            });



            StockBasicDto stockBasicDto = stockBasicDtos.get(stockBasicDtos.size() -1);

            plateStatInfoDTO.setMaxPe(stockBasicDto.getPe());
            if (stockBasicDto.getName() == null || stockBasicDto.getName().length() == 0) {
                InfoDao infoDao = stockInfoMapper.getStockInfo(stockBasicDto.getCode());
                if (infoDao != null) {
                    plateStatInfoDTO.setMaxPeName(infoDao.getName());
                }
            }else{
                plateStatInfoDTO.setMaxPeName(stockBasicDto.getName());
            }

            plateStatInfoDTO.setMaxPeCode(stockBasicDto.getCode());

            int validCount = 0;
            float sumPe = 0;
            for (StockBasicDto basicDto : stockBasicDtos){
                if (basicDto.getPe() <= 0){
                    continue;
                }
                if (validCount == 0){
                    if (basicDto.getName() == null || basicDto.getName().length() == 0) {
                        InfoDao infoDao = stockInfoMapper.getStockInfo(basicDto.getCode());
                        if (infoDao != null) {
                            plateStatInfoDTO.setMinPeName(infoDao.getName());
                        }
                    }else{
                        plateStatInfoDTO.setMinPeName(basicDto.getName());
                    }
                    plateStatInfoDTO.setMinPeCode(basicDto.getCode());
                    plateStatInfoDTO.setMinPe(basicDto.getPe());
                }
                validCount++;
                sumPe += basicDto.getPe();
            }
            if (validCount > 0) {
                plateStatInfoDTO.setAveragePe(sumPe / validCount);
            }

            try {
                //handle pb
                stockBasicDtos.sort(new Comparator<StockBasicDto>() {
                    @Override
                    public int compare(StockBasicDto o1, StockBasicDto o2) {

                        int o1pb = (int) (o1.getPb()*100);
                        int o2pb = (int) (o2.getPb()*100);

                        //o1 - o2 must equal = -(o2 - o1)

                        return o1pb - o2pb;
                        //return (int) (o1.getPb() - o2.getPb());
                    }
                });
            }catch (Exception e){
                logger.error(e.getLocalizedMessage());
                e.printStackTrace();

                /*
                for (StockBasicDto sdto : stockBasicDtos){
                    logger.error("stock: "+sdto.getCode()+" pb "+sdto.getPb());
                }
                */

            }
            stockBasicDto = stockBasicDtos.get(stockBasicDtos.size() -1);

            plateStatInfoDTO.setMaxPb((float) stockBasicDto.getPb());
            if (stockBasicDto.getName() == null || stockBasicDto.getName().length() == 0) {
                InfoDao infoDao = stockInfoMapper.getStockInfo(stockBasicDto.getCode());
                if (infoDao != null) {
                    plateStatInfoDTO.setMaxPbName(infoDao.getName());
                }
            }else{
                plateStatInfoDTO.setMaxPbName(stockBasicDto.getName());
            }

            plateStatInfoDTO.setMaxPbCode(stockBasicDto.getCode());

            validCount = 0;
            float sumPb = 0;
            for (StockBasicDto basicDto : stockBasicDtos){
                if (basicDto.getPb() <= 0){
                    continue;
                }
                if (validCount == 0){
                    InfoDao infoDao = stockInfoMapper.getStockInfo(basicDto.getCode());
                    if (basicDto.getName() == null || basicDto.getName().length() == 0) {
                        if (infoDao != null) {
                            plateStatInfoDTO.setMinPbName(infoDao.getName());
                        }
                    }else{
                        plateStatInfoDTO.setMinPbName(infoDao.getName());
                    }
                    plateStatInfoDTO.setMinPbCode(basicDto.getCode());
                    plateStatInfoDTO.setMinPb((float) basicDto.getPb());
                }
                validCount++;
                sumPb += basicDto.getPb();
            }
            if (validCount > 0) {
                plateStatInfoDTO.setAveragePb(sumPb / validCount);
            }

            logger.info("handle "+name+" done");

            plateStatInfoDTOList.add(plateStatInfoDTO);

        }

        logger.info("all done>>>>");
        String json =  ResponseUtil.successWithModel(plateStatInfoDTOList);

        return json;

    }

    @RequestMapping(value = "industryallstocks")
    public String industryAllStocks(@RequestParam(name = "iname" ) String iname){


        List<StockBasicDto> stockBasicDtos = plateInfoService.getIndustryStockInfo(iname);

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

        for (StockBasicDto dao : stockBasicDtos){
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

}
