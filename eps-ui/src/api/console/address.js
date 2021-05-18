import request from '@/utils/request'

// 查询发货地址列表
export function listAddress(query) {
  return request({
    url: '/console/address/list',
    method: 'get',
    params: query
  })
}

// 查询发货地址详细
export function getAddress(id) {
  return request({
    url: '/console/address/' + id,
    method: 'get'
  })
}

// 新增发货地址
export function addAddress(data) {
  return request({
    url: '/console/address',
    method: 'post',
    data: data
  })
}

// 修改发货地址
export function updateAddress(data) {
  return request({
    url: '/console/address',
    method: 'put',
    data: data
  })
}

// 删除发货地址
export function delAddress(id) {
  return request({
    url: '/console/address/' + id,
    method: 'delete'
  })
}
