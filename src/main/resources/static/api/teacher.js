// 查询列表数据
const getTeacherPage = (params) => {
  return $axios({
    url: '/teacher/page',
    method: 'get',
    params
  })
}

// 删除数据接口
const deleteTeacher = (ids) => {
  return $axios({
    url: '/teacher',
    method: 'delete',
    params: { ids }
  })
}

// 修改数据接口
const editTeacher = (params) => {
  return $axios({
    url: '/teacher',
    method: 'put',
    data: { ...params }
  })
}

// 新增数据接口
const addTeacher = (params,count) => {
  return $axios({
    url: '/teacher/add/'+ count,
    method: 'post',
    data: { ...params }
  })
}

// 查询详情接口
const queryTeacherById = (id) => {
  return $axios({
    url: `/teacher/query/`+ id,
    method: 'get'
  })
}

// 批量起售禁售
const TeacherStatusByStatus = (params) => {
  return $axios({
    url: `/teacher/status/${params.status}`,
    method: 'post',
    params: { ids: params.ids }
  })
}
