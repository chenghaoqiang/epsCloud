import request from '@/utils/request'

// 查询用户账户列表
export function listAccount(query) {
  return request({
    url: '/console/account/list',
    method: 'get',
    params: query
  })
}

// 查询用户账户详细
export function getAccount(id) {
  return request({
    url: '/console/account/' + id,
    method: 'get'
  })
}

// 新增用户账户
export function addAccount(data) {
  return request({
    url: '/console/account',
    method: 'post',
    data: data
  })
}

// 修改用户账户
export function updateAccount(data) {
  return request({
    url: '/console/account',
    method: 'put',
    data: data
  })
}

// 删除用户账户
export function delAccount(id) {
  return request({
    url: '/console/account/' + id,
    method: 'delete'
  })
}
