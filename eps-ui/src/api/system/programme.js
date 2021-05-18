import request from '@/utils/request'

// 查询作业方案信息列表
export function listProgramme(query) {
  return request({
    url: '/system/programme/list',
    method: 'get',
    params: query
  })
}

// 查询作业方案信息详细
export function getProgramme(objId) {
  return request({
    url: '/system/programme/' + objId,
    method: 'get'
  })
}

// 新增作业方案信息
export function addProgramme(data) {
  return request({
    url: '/system/programme',
    method: 'post',
    data: data
  })
}

// 修改作业方案信息
export function updateProgramme(data) {
  return request({
    url: '/system/programme',
    method: 'put',
    data: data
  })
}

// 删除作业方案信息
export function delProgramme(objId) {
  return request({
    url: '/system/programme/' + objId,
    method: 'delete'
  })
}
