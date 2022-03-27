#Interface definition general rules description
* 1. The server returns the result to the client in JSON format, the structure is as follows:<br/>
     Single data:<br/>{"code":200,"message":"","data":{…}}
2. Access address: obtained elsewhere;


# Basic ability Base
## Scene category order
### Create Order
* API declaration

createOrder()

* Request address: /api/order/createOrder
* Request method: GET
* Interface description: The merchant server obtains the address of the user's payment page, jumps from the merchant page to the generated address page, and the user completes the payment on this address page;
* Parameter description

| Field Name | Type | Required | Description |
|------|-------|----|----|
| merchantId | Long | Yes | Merchant ID, obtained by the user after corresponding authorization |
| amount | BigDecimal | Yes | Order amount, the amount the user needs to pay |
| outOrderNo | string | yes | order number, the user's unique order number on the merchant platform |
| sign | string | Yes | The signature, the user obtains by encrypting according to the corresponding rules |


* Explanation of parameters

| Field Name | Type | Description |
|------|--------|----|
| url | string | Address, user pay jump page address |


````json
{
    "code": 200,
    "message": "Success",
    "data": {
        "url": "go to Url"
    }
}

````

# Basic ability Base
## Scene category order
### Get order details
* API declaration

getInfo()

* Request address: /api/order/getInfo
* Request method: GET
* Interface description: Get order details
* Parameter description

| Field Name | Type | Required | Description |
|------|-------|----|----|
| merchantId | Long | Yes | Merchant ID, obtained by the user after corresponding authorization |
| outOrderNo | string | yes | order number, the user's unique order number on the merchant platform |
| sign | string | Yes | The signature, the user obtains by encrypting according to the corresponding rules |


* Explanation of parameters

| Field Name | Type | Description |
|------|--------|----|
| orderId | string | order number, platform order number |
| outOrderNo | string | Order number, the user's unique order number on the merchant platform |
| merchantId | string | Merchant ID, obtained by the user after corresponding authorization |
| txHash | string | The transaction hash, the address where the transaction occurred |
| amount | BigDecimal | Amount, the amount of payment required by the merchant to initiate the payment (USDT) |
| orderStatus | int | order status, 100 database has been stored, 200 transaction is successful, 300 has been refunded |
| fromAddress | string | source address, payment address |
| pushStatus | int | Push status, 0 for push, 200 for push success, 500 for push failure |
| pushSuccessTime | string | Push success time |
| pushReMsg | string | Push the return message, the latest push message returned by the merchant |
| contractAddress | string | contract address, the contract address where the transaction occurred |
| blockNumber | string | Block height, the height of the block where the transaction occurred |
| orderAmount | BigDecimal | Transaction amount, on-chain feedback user payment amount (USDT) |
| payAmount | BigDecimal | Amount of currency paid by users |
| payToken | string | User payment currency address |
| timestamp | string | Payment timestamp |
| chainType | string | Chain type, the name of the chain where the transaction occurred |

````json
{
    "code": 200,
    "message": "Success",
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

````