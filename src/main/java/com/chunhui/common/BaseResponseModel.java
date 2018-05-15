package com.yunqixie.common;

import lombok.Data;

@Data
public class BaseResponseModel {

    private int code;
    private Object data;
    private String msg;


    public BaseResponseModel(int code , Object data){
        this.code = code;
        this.data = data;
        this.msg  = "SUCCESS";
    }

    public BaseResponseModel(int code , String msg){
        this.code = code;
        this.msg = msg;
    }
}
