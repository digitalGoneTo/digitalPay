package com.example.demo.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddOrderBo {
    private Long merchantId;
    private BigDecimal amount;
    private String outOrderNo;
    private String sign;
}
