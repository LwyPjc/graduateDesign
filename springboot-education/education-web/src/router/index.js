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
          path: '/eduStudent',
          name: 'eduStudent',
          component: () => import('@/views/eduStudent/EduStudent')
        },
        {
          path: '/data',
          name: 'data',
          component: () => import('@/views/data/data')
        },
        {
          path: '/updatePwd',
          name: 'updatePwd',
          component: () => import('@/views/data/updatePwd')
        },
        {
          path: '/chooseCourse',
          name: 'chooseCourse',
          component: () => import('@/views/data/chooseCourse')
        },
        {
          path: '/timetable',
          name: 'timetable',
          component: () => import('@/views/data/timetable')
        },
        {
          path: '/eduClass',
          name: 'eduClass',
          component: () => import('@/views/eduClass/EduClass')
        },
        {
          path: '/eduClassRoom',
          name: 'eduClassRoom',
          component: () => import('@/views/eduClassRoom/EduClassRoom')
        },
        {
          path: '/eduCourse',
          name: 'eduCourse',
          component: () => import('@/views/eduCourse/EduCourse')
        },
        {
          path: '/eduOpenCourse',
          name: 'eduOpenCourse',
          component: () => import('@/views/eduOpenCourse/EduOpenCourse')
        },
        {
          path: '/eduStudentCourse',
          name: 'eduStudentCourse',
          component: () => import('@/views/eduStudentCourse/EduStudentCourse')
        },
        {
          path: '/eduTeacher',
          name: 'eduTeacher',
          component: () => import('@/views/eduTeacher/EduTeacher')
        }
      ]
    }
  ]
})
