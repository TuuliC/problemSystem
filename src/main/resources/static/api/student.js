// 查询列表数据
const getStudentPage = (params) => {
  return $axios({
    url: '/student/page',
    method: 'get',
    params
  })
}

// 删除数据接口
const deleteStudent = (ids) => {
  return $axios({
    url: '/student',
    method: 'delete',
    params: { ids }
  })
}

// 修改数据接口
const editStudent = (params) => {
  return $axios({
    url: '/student',
    method: 'put',
    data: { ...params }
  })
}

// 新增数据接口
const addStudent = (params,count) => {
  return $axios({
    url: '/student/add/'+ count,
    method: 'post',
    data: { ...params }
  })
}

// 查询详情接口
const queryStudentById = (id) => {
  return $axios({
    url: `/student/query/`+ id,
    method: 'get'
  })
}

// 批量起售禁售
const StudentStatusByStatus = (params) => {
  return $axios({
    url: `/student/status/${params.status}`,
    method: 'post',
    params: { ids: params.ids }
  })
}

const getClasses = () => {
  return $axios({
    url:'/class/classes',
    method:'get'
  })
}
