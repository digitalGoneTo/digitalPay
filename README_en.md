[![Maven Central](https://img.shields.io/maven-central/v/com.alipay.sdk/alipay-easysdk.svg)](https://mvnrepository.com/artifact/com.alipay.sdk/alipay-easysdk)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Falipay%2Falipay-easysdk.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Falipay%2Falipay-easysdk?ref=badge_shield)

Welcome to digital pay for Java.
[Chinese API](./README.md)
## Environmental requirements
* 1.example demo needs to work with 'JDK 1.8' or above;
* 2. Before using the example demo, you need to complete some preparations for the developer, including applying to become a merchant, obtaining the merchant key, setting the callback address, etc.
* 3. After the preparation work is completed, pay attention to save the following information, which will be used as the input for using the demo later:
    * 'Merchant ID=>merchantId', 'Payment background address=>SERVICE_URL', 'Merchant key=>SECRET_KEY'
## quick start
Clone this repository code to local
The following code shows you the steps to call the API using the example demo:
    * 1. Set global parameters (the global only needs to be set once)
    * 2. Initiate an API call
    * 3. Handle the response or exception
## Note that this is part of the code, please download the complete code to run
```java
package com.example.demo.content;

/**
*
 */
public interface Content {
    //Set the global parameters (the global only needs to be set once)
    String SECRET_KEY  = "sssaaaaaa" ;
    Long MERCHANT_ID = 1497108653939908609L ;
    String SERVICE_URL = "http://127.0.0.1:8080" ;
}
```
```java 
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

````
## Supported API list
| Capability category | Scenario category | Interface method name | OpenAPI address to call |
|----------|-----------------|------------------- ----|-------------------------------------------- --------------|
| base | order | createOrder | /api/order/createOrder |
| base | order | getInfo | /api/order/getInfo |


> Note: APIs for more high-frequency scenarios are continuously updated, so stay tuned.

## Price tag principle

* 1. Get all POST content, excluding byte-type parameters, such as files, byte streams, remove the sign field, and remove parameters with empty values;
* 2. Sort in ascending order according to the key value ASCII code of the first character (in ascending alphabetical order), if the same character is encountered, it will be sorted in ascending order according to the key value ASCII code of the second character, and so on;
* 3. Combine the sorted parameters and their corresponding values into the format of parameter=parameter value, connect these parameters with the & character, and then splicing your merchant key at the end, the generated string is to be signed string;
* 4. The string to be signed is encrypted by MD5, and the value of the signature string sign is generated;


You can also go to [API Doc](./APIDocEn.md) to view detailed usage instructions for each API.
