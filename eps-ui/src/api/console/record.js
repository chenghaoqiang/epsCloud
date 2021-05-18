import request from '@/utils/request'

// 查询账户流水列表
export function listRecord(query) {
  return request({
    url: '/console/record/list',
    method: 'get',
    params: query
  })
}

// 查询账户流水详细
export function getRecord(id) {
  return request({
    url: '/console/record/' + id,
    method: 'get'
  })
}

// 新增账户流水
export function addRecord(data) {
  return request({
    url: '/console/record',
    method: 'post',
    data: data
  })
}

// 修改账户流水
export function updateRecord(data) {
  return request({
    url: '/console/record',
    method: 'put',
    data: data
  })
}

// 删除账户流水
export function delRecord(id) {
  return request({
    url: '/console/record/' + id,
    method: 'delete'
  })
}
