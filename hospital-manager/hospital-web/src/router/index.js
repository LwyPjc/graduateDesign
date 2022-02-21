import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      component: () => import('@/views/login')
    },
    {
      path: '/',
      component: () => import('@/views/Menu'),
      // component: () => import('@/components/HeaderMenu/index'),
      children: [
        {
          path: '/department',
          name: 'Department',
          component: () => import('@/views/department/Department')
        },
        {
          path: '/updatePwd',
          name: 'updatePwd',
          component: () => import('@/views/data/updatePwd')
        },
        {
          path: '/doctorInfo',
          name: 'DoctorInfo',
          component: () => import('@/views/doctorInfo/DoctorInfo')
        },
        {
          path: '/feedbackInfo',
          name: 'FeedbackInfo',
          component: () => import('@/views/feedbackInfo/FeedbackInfo')
        },
        {
          path: '/userInfo',
          name: 'UserInfo',
          component: () => import('@/views/userInfo/UserInfo')
        },
        // {
        //   path: '/statistics',
        //   name: 'Statistics',
        //   component: () => import('@/views/statistics/Statistics')
        // },
      ]
    }
  ]
})
