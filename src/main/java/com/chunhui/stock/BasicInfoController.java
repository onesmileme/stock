package com.chunhui.stock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicInfoController {

    @RequestMapping("/basicinfo")
    public String basicInfo(){

        return "<html><body>BasicInfo</body></html>";
    }
}
