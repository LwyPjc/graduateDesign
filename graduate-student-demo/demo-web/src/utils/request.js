import axios from 'axios'
import { MessageBox, Message } from 'element-ui'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 10 * 60000// 超时时间10分钟,
})

service.all = axios.all
service.spread = axios.spread

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    if (response.headers &&
          (response.headers['content-type'].toLowerCase()
            .indexOf('application/vnd.ms-excel') >
              -1 ||
              response.headers['content-type'].toLowerCase()
                .indexOf('application/octet-stream') >
              -1 ||
              response.headers['content-type'].toLowerCase()
                .indexOf('application/pdf') >
              -1 ||
              response.headers['content-type'].toLowerCase().indexOf(
                'application/vnd.oasis.opendocument.formula') >
              -1 ||
              response.headers['content-type'].toLowerCase().indexOf(
                'application/vnd.oasis.opendocument.text') >
              -1)) {
      return response
    }

    // if the custom code is not 20000, it is judged as an error.
    if (response.status !== 200) {
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })

      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
          confirmButtonText: 'Re-Login',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
        })
      }
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
