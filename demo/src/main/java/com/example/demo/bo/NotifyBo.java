package com.example.demo.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;


@Data

public class NotifyBo {

    private String sign;


    private String outOrderNo;


    private BigInteger blockNumber;


    private Long merchantId;


    private String contractAddress;


    private String txHash;


    private BigDecimal amount;


    private BigDecimal orderAmount;


    private String payToken;


    private BigDecimal payAmount;


    private Long timestamp;


    private String fromAddress;


    private int pushCode;


}
