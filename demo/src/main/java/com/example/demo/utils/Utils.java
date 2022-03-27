package com.example.demo.utils;


import java.util.Map;

public class Utils {
    public static String getParamsUrl(String baseUrl, Map<String, Object> params ){
        String s = EncryptUtil.getStringBuilder(params).toString();
        return baseUrl+"?"+s;
    }
}
