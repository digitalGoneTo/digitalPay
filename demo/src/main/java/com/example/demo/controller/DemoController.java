package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.example.demo.bo.AddOrderBo;
import com.example.demo.bo.GetOrderInfoBo;
import com.example.demo.bo.NotifyBo;
import com.example.demo.content.Content;
import com.example.demo.utils.EncryptUtil;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;


@RestController
@RequestMapping("/demo")
public class DemoController {
    /**
     * create order
     *
     * @return
     */
    @GetMapping("/createOrder")
    public String createOrder(@RequestParam("orderId") String orderId,@RequestParam("amount")String amountStr) {
        BigDecimal amount = new BigDecimal(amountStr);
        AddOrderBo createOrderBo = new AddOrderBo();
        createOrderBo.setOutOrderNo(orderId);
        createOrderBo.setAmount(amount);
        createOrderBo.setMerchantId(Content.MERCHANT_ID);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(createOrderBo);
        String sign = EncryptUtil.createSign(stringObjectMap, Content.SECRET_KEY);
        stringObjectMap.put("sign", sign);
        return HttpUtil.get(Content.SERVICE_URL + "/api/order/createOrder", stringObjectMap);
    }

    /**
     * notifyUrl
     *
     * @return
     */
    @PostMapping("/notifyUrl")
    public String notifyUrl(@RequestBody NotifyBo bo) {
        String sign = bo.getSign();
        bo.setSign(null);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(bo);
        checkSign(stringObjectMap, sign);

        // TODO  your demo

        return "success";

    }
    private void checkSign(Map<String, Object> stringObjectMap, String sign) {
        String sign1 = EncryptUtil.createSign(stringObjectMap, Content.SECRET_KEY);
        if (!sign1.equalsIgnoreCase(sign)) {
            throw new Error("sign error");
        }
    }

    /**
     * getOrderInfo
     *
     * @return
     */
    @GetMapping("/getOrderInfo")
    public String getOrderInfo(@RequestParam("outOrderNo")String outOrderNo ) {
        GetOrderInfoBo getOrderInfoBo = new GetOrderInfoBo();
        getOrderInfoBo.setOutOrderNo(outOrderNo);
        getOrderInfoBo.setMerchantId(Content.MERCHANT_ID);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(getOrderInfoBo);
        String sign = EncryptUtil.createSign(stringObjectMap, Content.SECRET_KEY);
        stringObjectMap.put("sign",sign);
        return HttpUtil.get(Content.SERVICE_URL + "/api/order/getInfo", stringObjectMap);
    }
}
