import Vue from "vue"
import Router from "vue-router"
import Home from "./views/Home.vue"
import Profile from "./views/Profile.vue"
import RegisterProduct from "./views/RegisterProduct.vue"
import ShoppingCart from "./views/ShoppingCart.vue"
import Login from "./modal/Login.vue"
Vue.use(Router)

export default new Router ({
    routes: [{
        path:"/",
        name:"Home",
        component: Home
    },{
        path:"/Profile",
        name:"Profile",
        component: Profile
    },{
        path:"/RegisterProduct",
        name:"RegisterProduct",
        component: RegisterProduct
    },{
        path:"/ShoppingCart",
        name:"ShoppingCart",
        component: ShoppingCart
    },{
        path:"/Login",
        name:"Login",
        component: Login
    }]
})
