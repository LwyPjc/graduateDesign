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
          path: '/student',
          name: 'Student',
          component: () => import('@/views/student/Student')
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
          path: '/aClass',
          name: 'Class',
          component: () => import('@/views/class/Class')
        },
        {
          path: '/classRoom',
          name: 'ClassRoom',
          component: () => import('@/views/classRoom/ClassRoom')
        },
        {
          path: '/course',
          name: 'Course',
          component: () => import('@/views/course/Course')
        },
        {
          path: '/openCourse',
          name: 'OpenCourse',
          component: () => import('@/views/openCourse/OpenCourse')
        },
        {
          path: '/studentCourse',
          name: 'StudentCourse',
          component: () => import('@/views/studentCourse/StudentCourse')
        },
        {
          path: '/teacher',
          name: 'Teacher',
          component: () => import('@/views/teacher/Teacher')
        },
        {
          path: '/warningRule',
          name: 'WarningRule',
          component: () => import('@/views/warningRule/WarningRule')
        },
        {
          path: '/participationEvaluate',
          name: 'ParticipationEvaluate',
          component: () => import('@/views/evaluate/ParticipationEvaluate')
        },
        {
          path: '/scoreEvaluate',
          name: 'ScoreEvaluate',
          component: () => import('@/views/evaluate/ScoreEvaluate')
        },
        {
          path: '/studentEvaluate',
          name: 'StudentEvaluate',
          component: () => import('@/views/evaluate/StudentEvaluate')
        },
        {
          path: '/taskEvaluate',
          name: 'TaskEvaluate',
          component: () => import('@/views/evaluate/TaskEvaluate')
        },
      ]
    }
  ]
})
