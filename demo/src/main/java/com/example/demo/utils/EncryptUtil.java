package com.example.demo.utils;

import com.sun.istack.internal.NotNull;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;


public class EncryptUtil {


    /**
     * sign
     *
     */
    public static String createSign(Map<String, Object> params,String secretKey) {
        StringBuilder sb = getStringBuilder(params);
        return toMD5(sb.append(secretKey).toString());
    }

    @NotNull
    public static StringBuilder getStringBuilder(Map<String, Object> params) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        // Sort parameters in ascending lexicographical order of parameter names
        Map<String, Object> sortParams = new TreeMap<>(params);
        // Traverse the sorted dictionary and concatenate the "key=value" format
        for (Map.Entry<String, Object> entry : sortParams.entrySet()) {
            String key = entry.getKey();
            if(entry.getValue()!=null){
                Object value = ( entry.getValue()).toString().trim();
                if (!StringUtils.isEmpty(value)) {
                    if (i != 0)
                        sb.append("&");
                    sb.append(key).append("=").append(value);
                }
            }
            i++;
        }
        return sb;
    }


    public static String toMD5(String str) {

        String md5 = "";
        try {
            md5 = DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return md5;
    }

}
