package com.chunhui.stock;

import com.chunhui.core.dto.InfoDao;
import com.chunhui.core.dto.StockBasicDto;
import com.chunhui.core.dto.StockBasicMapper;
import com.chunhui.core.dto.StockInfoMapper;
import javafx.beans.binding.StringBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class BasicInfoController {

    @Autowired
    private StockBasicMapper basicMapper;

    @Autowired
    private StockInfoMapper infoMapper;

    @RequestMapping("/basicinfo")
    public String basicInfo(){

        return "<html><body>BasicInfo</body></html>";
    }

    @RequestMapping("/sample")
    public String getSample(HttpServletRequest request , HttpServletResponse response){

        StringBuilder html = new StringBuilder();

        StringBuffer url = request.getRequestURL();

        html.append("<br /> url <br />");
        html.append(url.toString());
        html.append("<br />");

        String path = request.getPathInfo();

        html.append("<br /> path info <br/>");
        html.append(path);
        html.append("<br/>");

        String query = request.getQueryString();

        html.append("query : <br/>");
        html.append(query);
        html.append("<br/>");



        return html.toString();
    }

    @RequestMapping("/allbasicstocks")
    public String allBasicInfo(HttpServletRequest request) {

        StringBuffer info = new StringBuffer();

        //List<StockBasicDao> allinfos = basicMapper.getAllBasicInfo();

        //*
        List<InfoDao> allInfos = infoMapper.getAllInfos();

        info.append("<table border=\"1\"><thead><tr><th>Name</th><th>Code</th></thead>");
        info.append("<tbody>");
        for (InfoDao dao : allInfos) {
            info.append("<tr><td>"+dao.getCode()+"</td><td>"+dao.getName()+"</td></tr>");
        }

        info.append("</tbody></table>");

        return info.toString();
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insert(@RequestParam("code") String code , @RequestParam("name") String name){

        return "{\"code\":0 ,\"code\":"+code+",name:"+name+"}";
    }

    @RequestMapping(value = "/todayquote")
    public String todayFundamentInfo(){


        return null;
    }

}
