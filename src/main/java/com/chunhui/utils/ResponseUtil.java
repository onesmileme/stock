package com.chunhui.utils;


import com.alibaba.fastjson.JSONObject;
import com.yunqixie.common.BaseResponseModel;

public class ResponseUtil {

    public static  String successWithModel(Object object){

        BaseResponseModel model = new BaseResponseModel(0,object);
        String json =  JSONObject.toJSONString(model);
        //System.out.println("json is: \n"+json);
        return json;
    }

    public  static  String failed(int code , String msg){

        BaseResponseModel model = new BaseResponseModel(code,msg);
        return JSONObject.toJSONString(model);
    }
}
