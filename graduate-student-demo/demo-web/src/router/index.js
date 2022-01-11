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
          path: '/dataAnalysis',
          name: 'dataAnalysis',
          component: () => import('@/views/dataAnalysis')
        },
        {
          path: '/sysCategory',
          name: 'sysCategory',
          component: () => import('@/views/sysCategory/SysCategory')
        },
        {
          path: '/ztAdvice',
          name: 'ztAdvice',
          component: () => import('@/views/ztAdvice/ZtAdvice')
        },
        {
          path: '/ztCollect',
          name: 'ztCollect',
          component: () => import('@/views/ztCollect/ZtCollect')
        },
        {
          path: '/ztDonate',
          name: 'ztDonate',
          component: () => import('@/views/ztDonate/ZtDonate')
        },
        {
          path: '/ztGoods',
          name: 'ztGoods',
          component: () => import('@/views/ztGoods/ZtGoods')
        },
        {
          path: '/sysSetting',
          name: 'sysSetting',
          component: () => import('@/views/setting/SysSetting')
        }
      ]
    }
  ]
})
