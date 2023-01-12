// 查询列表数据
const getCoursePage = (params) => {
  return $axios({
    url: '/course/page',
    method: 'get',
    params
  })
}

// 删除数据接口
const deleteCourse = (ids) => {
  return $axios({
    url: '/course',
    method: 'delete',
    params: { ids }
  })
}

// 修改数据接口
const editCourse = (params) => {
  return $axios({
    url: '/course',
    method: 'put',
    data: { ...params }
  })
}

// 新增数据接口
const addCourse = (param) => {
  return $axios({
    url: '/course/add/'+ param,
    method: 'post',
  })
}

// 查询详情接口
const queryCourseById = (id) => {
  return $axios({
    url: `/course/query/`+ id,
    method: 'get'
  })
}

// 批量起售禁售
const CourseStatusByStatus = (params) => {
  return $axios({
    url: `/course/status/${params.status}`,
    method: 'post',
    params: { ids: params.ids }
  })
}

//教师添加课程
const addCourseByTeacher = (username,id) =>{
  return $axios({
    url:'/course/addCourse?id=' + id,
    method:'post',
    username : username
  })
}
addClassByTeacher= (courseId,classId,userId) =>{
  return $axios({
    url:'/course/addClass',
    method:'post',
    data:{
      courseId : courseId,
      classId:classId,
      userId:userId
    }

  })
}
