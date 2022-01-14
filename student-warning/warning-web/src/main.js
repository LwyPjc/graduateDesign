// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import globalConstant from '@/components/constants'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(ElementUI)

Vue.config.productionTip = false
Vue.prototype.GLOBAL = globalConstant
/* eslint-disable no-new */
router.beforeEach((to, from, next) => {
  document.title = "学生监管系统";
  if (to.path === "/login") {
    window.sessionStorage.removeItem("username");
    next();
  } else if (window.sessionStorage.username) {
    next();
  } else {
    next("/login");
  }

});
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
});
