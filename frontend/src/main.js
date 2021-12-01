import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Home from "./views/Home.vue"
import Profile from "./views/Profile.vue"
import RegisterProduct from "./views/RegisterProduct.vue"
import RegisterUser from "./modal/RegisterUser.vue"
import RegisterAddress from "./modal/RegisterAddress.vue"
import RegisterAuthor from "./modal/RegisterAuthor.vue"
import RegisterCategory from "./modal/RegisterCategory.vue"
import RegisterCompany from "./modal/RegisterCompany.vue"
import UpdateAuthor from "./modal/UpdateAuthor.vue"
import UpdateCategory from "./modal/UpdateCategory.vue"
import UpdateCompany from "./modal/UpdateCompany.vue"
import DeleteAuthor from "./modal/DeleteAuthor.vue"
import DeleteCategory from "./modal/DeleteCategory.vue"
import Recovery from "./modal/Recovery.vue"
import ShoppingCart from "./views/ShoppingCart.vue"
import Login from "./modal/Login.vue"
import NavBar from "./components/NavBar.vue"
import SideBar from "./components/SideBar.vue"
import Footer from "./components/Footer.vue"
import Product from "./views/Product.vue"
import Payment from "./views/Payment.vue"
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
Vue.component("RegisterUser",RegisterUser);
Vue.component("RegisterAddress",RegisterAddress);
Vue.component("RegisterAuthor",RegisterAuthor);
Vue.component("RegisterCategory",RegisterCategory);
Vue.component("RegisterCompany",RegisterCompany);
Vue.component("UpdateAuthor",UpdateAuthor);
Vue.component("UpdateCategory",UpdateCategory);
Vue.component("UpdateCompany",UpdateCompany);
Vue.component("DeleteAuthor",DeleteAuthor);
Vue.component("DeleteCategory",DeleteCategory);
Vue.component("Recovery",Recovery);
Vue.component("Login",Login);
Vue.component("Product",Product);
Vue.component("Payment",Payment);
Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
