#接口定义通用规则说明
* 1.服务端以JSON格式返回结果给客户端，结构如下：<br/>
 单条数据：<br/>{"code":200,"message":"","data":{…}}
2.	访问地址：他處獲得;


# 基礎能力 Base
## 場景類別 order
### 創建訂單
* API聲明

createOrder()

* 請求地址: /api/order/createOrder
* 請求方式: GET
* 接口説明: 商戶服務端獲取用戶支付頁面地址,由商戶頁面跳轉到生成的地址頁面,用戶在此地址頁面完成支付;
* 入參説明

| 字段名  | 類型     | 必填 | 説明 |
|------|--------|----|----|
| merchantId | Long | 是  |  商戶號，用戶對應授權后得到  |
| amount | BigDecimal | 是  |  訂單金額，用戶所需支付數額  |
| outOrderNo | string | 是  |  訂單號，用戶在商戶平臺的唯一訂單號  |
| sign | string | 是  |  簽名，用戶按照對應規則加密獲得  |


* 出參説明

| 字段名  | 類型      | 説明 |
|------|--------|----|
| url | string  |  地址，用戶支付跳轉頁面地址  |


```json
{
    "code": 200,
    "message": "成功",
    "data": {
        "url": "go to Url"
    }
}

```

# 基礎能力 Base
## 場景類別 order
### 獲取訂單詳情
* API聲明

getInfo()

* 請求地址: /api/order/getInfo
* 請求方式: GET
* 接口説明: 獲取訂單詳情
* 入參説明

| 字段名  | 類型     | 必填 | 説明 |
|------|--------|----|----|
| merchantId | Long | 是  |  商戶號，用戶對應授權后得到  |
| outOrderNo | string | 是  |  訂單號，用戶在商戶平臺的唯一訂單號  |
| sign | string | 是  |  簽名，用戶按照對應規則加密獲得  |


* 出參説明

| 字段名  | 類型      | 説明 |
|------|--------|----|
| orderId | string  |  訂單號，平臺訂單編號  |
| outOrderNo | string |  訂單號，用戶在商戶平臺的唯一訂單號  |
| merchantId | string  |  商戶號，用戶對應授權后得到  |
| txHash | string |  交易哈希，交易發生位置地址  |
| amount | BigDecimal |  數額，商戶發起所需支付數額(USDT)   |
| orderStatus | int |  訂單狀態，100 數據庫已儲存,200 交易成功,300 已退款  |
| fromAddress | string |  來源地址，付款地址 |
| pushStatus | int |  推送狀態，0 等待推送,200 推送成功,500 推送失敗 |
| pushSuccessTime | string |  推送成功時間 |
| pushReMsg | string |  推送返回消息,最進一次推送商戶返回的消息 |
| contractAddress | string |  合約地址,發生交易的合約地址 |
| blockNumber | string |  塊高度,發生交易的區塊高度 |
| orderAmount | BigDecimal |  交易金額,鏈上反饋用戶支付金額(USDT) |
| payAmount | BigDecimal |  用戶支付幣種數量 |
| payToken | string |  用戶支付幣種地址 |
| timestamp | string |  支付時間戳 |
| chainType | string |  鏈類型,發生交易的鏈名 |

```json
{
    "code": 200,
    "message": "成功",
    "data": {
        "orderId": "151231546321",
        "outOrderNo": "No155s",
        "merchantId": "1497108653939908609",
        "txHash": "0x54a5s6dsadsa5s6dsadsa5645s6dsadsa56456456as",
        "amount": 10,
        "orderStatus": 200,
        "fromAddress": "0x5asdaxa5s6dsadsa5645s6dsadsa5645s15",
        "pushStatus": 200,
        "pushSuccessTime": "2021-01-08 22:38:53",
        "pushReMsg": "success",
        "contractAddress": "0xxasadcbsad56a5sd5sa5cas",
        "blockNumber": "1756353",
        "orderAmount": 18,
        "payToken": "0xxasadcbsad56a5sd5sa5cas",
        "payAmount": 222,
        "timestamp": null,
        "chainType": "BSC"
    }
}

```

