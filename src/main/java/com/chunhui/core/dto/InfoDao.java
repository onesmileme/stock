package com.chunhui.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class InfoDao implements Serializable{

    protected String code;
    protected String name;

    @Override
    public  String toString(){
        return "code: "+code + "  name: "+name;
    }
}
