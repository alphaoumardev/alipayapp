import request from "../axios/request";


export const lists = (page, size, user) => {
  return request
  ({
    url: '/user/listpage',
    method: 'POST',
    params: {page, size},
    data: user
  })
}
export const deleteAvatar = (file) => {
  return request
  ({
    url: '/files/deleteAvatar',
    method: 'POST',
    params: {file},
  })
}
export const addUser = (user) => {
  return request
  ({
    url: '/user/adduser',
    method: 'POST',
    data: user
  })
}
