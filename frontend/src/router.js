import Vue from "vue"
import Router from "vue-router"
import Home from "./views/Home.vue"
import Profile from "./views/Profile.vue"
import RegisterProduct from "./views/RegisterProduct.vue"
import ShoppingCart from "./views/ShoppingCart.vue"
import RegisterUser from "./modal/RegisterUser.vue"
import RegisterAuthor from "./modal/RegisterAuthor.vue"
import RegisterCompany from "./modal/RegisterCompany.vue"
import RegisterCategory from "./modal/RegisterCategory.vue"
import RegisterAddress from "./modal/RegisterAddress.vue"
import Recovery from "./modal/Recovery.vue"
import Product from "./views/Product.vue"
import Payment from "./views/Payment.vue"
Vue.use(Router)

export default new Router({
    routes: [{
        path: "/",
        name: "Home",
        component: Home
    }, {
        path: "/Profile",
        name: "Profile",
        component: Profile
    }, {
        path: "/RegisterProduct",
        name: "RegisterProduct",
        component: RegisterProduct
    }, {
        path: "/ShoppingCart",
        name: "ShoppingCart",
        component: ShoppingCart
    }, {
        path: "/Login",
        name: "Login",
        redirect: {
            name: "Home",
            component: Home,
        },
    }, {
        path: "/RegisterUser",
        name: "RegisterUser",
        component: RegisterUser
    }, {
        path: "/RegisterAuthor",
        name: "RegisterAuthor",
        component: RegisterAuthor
    }, {
        path: "/RegisterCompany",
        name: "RegisterCompany",
        component: RegisterCompany
    }, {
        path: "/RegisterCategory",
        name: "RegisterCategory",
        component: RegisterCategory
    }, {
        path: "/RegisterAddress",
        name: "RegisterAddress",
        component: RegisterAddress
    }, {
        path: "/Recovery",
        name: "Recovery",
        component: Recovery
    }, {
        path: "/Product",
        name: "Product",
        component: Product
    },{
        path: "/Payment",
        name: "Payment",
        component: Payment
    }]
})