// 查询列表数据
const getTestPage = (params) => {
  return $axios({
    url: '/questions/page',
    method: 'get',
    params: params
  })
}

// 删除数据接口
const deleteTest = (ids) => {
  return $axios({
    url: '/test',
    method: 'delete',
    params: { ids }
  })
}

// 修改数据接口
const editTest = (params) => {
  return $axios({
    url: '/test',
    method: 'put',
    data: { ...params }
  })
}

// 新增数据接口
const addTest = (params) => {
  return $axios({
    url: '/questions/add',
    method: 'post',
    data: params,
    //dataType:'json',
    //contentType:'application/json;charset=utf-8',
    //headers: { 'Content-Type': 'application/json;charset=UTF-8'}
  })
}

// 查询详情接口
const queryTestById = (id) => {
  return $axios({
    url: `/test/query/`+ id,
    method: 'get'
  })
}
const getTypes =() =>{
  return $axios({
    url:'/test/category',
    method:'get'
  })
}
const getCourses =() =>{
  return $axios({
    url:'/course/getAll',
    method:'get'
  })
}
