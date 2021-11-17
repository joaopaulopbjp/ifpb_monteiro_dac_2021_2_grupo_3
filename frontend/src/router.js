import Vue from "vue"
import Router from "vue-router"
import Home from "./views/Home"
import Profile from "./views/Profile"
import RegisterProduct from "./views/RegisterProduct"
import ShoppingCart from "./views/ShoppingCart.vue"
Vue.use(Router)

export default new Router ({
    routes: [{
        path:"/",
        component: Home
    },{
        path:"/Profile",
        name:"Profile",
        component: Profile
    },{
        path:"/RegisterProduct",
        component: RegisterProduct
    },{
        path:"/ShoppingCart",
        component: ShoppingCart
    }]
})
