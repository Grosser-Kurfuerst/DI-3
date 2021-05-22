import Vue from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
// import 'ant-design-vue/dist/antd.less'
import store from './store'
import router from "@/router";

Vue.config.productionTip = false
Vue.use(Antd)
new Vue({
  render: h => h(App),
  store,
  router,
}).$mount('#app')
