function loginApi(data,remember) {
  return $axios({
    'url': '/count/login?remember='+remember,
    'method': 'post',
    data
  })
}

function logoutApi(){
  return $axios({
    'url': '/count/logout',
    'method': 'get',
  })
}
