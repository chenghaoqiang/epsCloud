import request from '@/utils/request'

// 微信Payjs扫码支付
export function wxPayjsNative(payAmount) {
  return request({
    url: '/pay/payjs/native',
    method: 'get',
    params: {"payAmount":payAmount}
  })
}

// 微信Payjs订单查询
export function wxPayjsCheck(payjsOrderId) {
  return request({
    url: '/pay/payjs/check',
    method: 'get',
    params: {"payjsOrderId":payjsOrderId}
  })
}
