import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Home from "./views/Home.vue"
import Profile from "./views/Profile.vue"
import RegisterProduct from "./views/RegisterProduct.vue"
import ShoppingCart from "./views/ShoppingCart.vue"
import Login from "./modal/Login.vue"
import NavBar from "./components/NavBar.vue"
import SideBar from "./components/SideBar.vue"
import Footer from "./components/Footer.vue"
import { BootstrapVue} from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue);
Vue.component("Home",Home);
Vue.component("Profile",Profile);
Vue.component("RegisterProduct",RegisterProduct);
Vue.component("ShoppingCart",ShoppingCart);
Vue.component("ShoppingCart",ShoppingCart);
Vue.component("NavBar",NavBar);
Vue.component("SideBar",SideBar);
Vue.component("Footer",Footer);
Vue.component("Login",Login);
Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
