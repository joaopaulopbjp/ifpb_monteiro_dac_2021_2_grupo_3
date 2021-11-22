import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Home from "./views/Home.vue"
import Profile from "./views/Profile.vue"
import RegisterProduct from "./views/RegisterProduct.vue"
import ShoppingCart from "./views/ShoppingCart.vue"
import { BootstrapVue} from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue);
Vue.component("Home",Home);
Vue.component("Profile",Profile);
Vue.component("RegisterProduct",RegisterProduct);
Vue.component("ShoppingCart",ShoppingCart);
Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
