import request from "../axios/request";

export const findandcount = () => {
  return request
  ({
    url: '/department/find',
    method: 'GET',
  })
}
