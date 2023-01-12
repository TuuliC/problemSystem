// 查询列表数据
const getClassPage = (params) => {
  return $axios({
    url: '/class/page',
    method: 'get',
    params
  })
}

// 删除数据接口
const deleteClass = (ids) => {
  return $axios({
    url: '/class',
    method: 'delete',
    params: { ids }
  })
}

// 修改数据接口
const editClass = (params) => {
  return $axios({
    url: '/class',
    method: 'put',
    data: { ...params }
  })
}

// 新增数据接口
const addClass = (params) => {
  return $axios({
    url: '/class',
    method: 'post',
    data: { ...params }
  })
}

// 查询详情接口
const queryClassById = (id) => {
  return $axios({
    url: `/class/query/`+ id,
    method: 'get'
  })
}

// 批量起售禁售
const ClassStatusByStatus = (params) => {
  return $axios({
    url: `/class/status/${params.status}`,
    method: 'post',
    params: { ids: params.ids }
  })
}
