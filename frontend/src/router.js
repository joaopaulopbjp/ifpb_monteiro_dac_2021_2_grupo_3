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
import UpdateAuthor from "./modal/UpdateAuthor.vue"
import UpdateCategory from "./modal/UpdateCategory.vue"
import UpdateCompany from "./modal/UpdateCompany.vue"
import DeleteAuthor from "./modal/DeleteAuthor.vue"
import DeleteCategory from "./modal/DeleteCategory.vue"
import DeleteCompany from "./modal/DeleteCompany.vue"
import DeleteProduct from "./modal/DeleteProduct.vue"
import UpdateProduct from "./views/UpdateProduct.vue"
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
        path: "/UpdateAuthor",
        name: "UpdateAuthor",
        component: UpdateAuthor
    }, {
        path: "/UpdateCategory",
        name: "UpdateCategory",
        component: UpdateCategory
    }, {
        path: "/UpdateCompany",
        name: "UpdateCompany",
        component: UpdateCompany
    }, {
        path: "/DeleteAuthor",
        name: "DeleteAuthor",
        component: DeleteAuthor
    }, {
        path: "/DeleteCategory",
        name: "DeleteCategory",
        component: DeleteCategory
    }, {
        path: "/DeleteCompany",
        name: "DeleteCompany",
        component: DeleteCompany
    },{
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
    },{
        path: "/UpdateProduct",
        name: "UpdateProduct",
        component: UpdateProduct
    },{
        path: "/DeleteProduct",
        name: "DeleteProduct",
        component: DeleteProduct
    }]
})