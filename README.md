[![Maven Central](https://img.shields.io/maven-central/v/com.alipay.sdk/alipay-easysdk.svg)](https://mvnrepository.com/artifact/com.alipay.sdk/alipay-easysdk)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Falipay%2Falipay-easysdk.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Falipay%2Falipay-easysdk?ref=badge_shield)

歡迎使用digital pay for Java.
[English API](./README_en.md)
## 環境要求
* 1.example demo 需要配合'JDK 1.8'  或其以上版本;
* 2.使用 example demo之前 您需要先完成開發者一些準備工作,包括申請成爲商戶,獲取商戶密鑰,設置回調地址等.
* 3.準備工作完成之後,注意保存如下信息,後續將作爲使用demo的輸入:
  * '商戶ID=>merchantId'、'支付後臺地址=>SERVICE_URL'、'商戶密鑰=>SECRET_KEY'
 ## 快速開始
 克隆此倉庫代碼到本地
 以下代碼向您展示使用example demo 調用API步驟:
 * 1.設置全局參數(全局只需要設置一次) 
 * 2.發起API調用
 * 3.處理響應或異常
 ## 注意此為部分代碼 請下載完整代碼運行
```java
package com.example.demo.content;

/**
*
 */
public interface Content {
    //設置全局參數(全局只需要設置一次) 
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

```
## 支持的API列表
| 能力類別   | 場景類別         | 接口方法名稱           | 調用的OpenAPI地址                                         |
|-----------|-----------------|-----------------------|-----------------------------------------------------------|
| base      | order           | createOrder            | /api/order/createOrder                                        |
| base      | order           | getInfo               | /api/order/getInfo                                        |

> 注：更多高頻場景的API持續更新中，敬請期待！

## 價簽原理

* 1.獲取所有POST内容,不包括字節類型參數,如文件、字節流,剔除sign字段,剔除值爲空的參數;
* 2.按照第一個字符的鍵值ASCII碼遞增排序(字母升序排序),如果遇到相同字符則按照第二個字符的鍵值ASCII碼遞增排序,以此類推;
* 3.將排序后的參數與其對應值,組合成 參數=參數值 的格式,并且把這些參數用 & 字符連接起來,然後最後拼接上您的商戶密鑰,此時生成的字符串為待簽名字符串;
* 4.待簽名字符串以MD5方式加密,生成的就是簽名字符串sign的值;


您還可以前往[API Doc](./APIDoc.md)查看每個API的詳細使用説明。
