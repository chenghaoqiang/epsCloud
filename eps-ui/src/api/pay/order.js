import request from '@/utils/request'

// 查询发货地址列表
export function listOrder(query) {
  return request({
    url: '/pay/order/list',
    method: 'get',
    params: query
  })
}

// 查询发货地址详细
export function getOrder(id) {
  return request({
    url: '/pay/order/' + id,
    method: 'get'
  })
}

// 新增发货地址
export function addOrder(data) {
  return request({
    url: '/pay/order',
    method: 'post',
    data: data
  })
}

// 修改发货地址
export function updateOrder(data) {
  return request({
    url: '/pay/order',
    method: 'put',
    data: data
  })
}

// 删除发货地址
export function delOrder(id) {
  return request({
    url: '/pay/order/' + id,
    method: 'delete'
  })
}
