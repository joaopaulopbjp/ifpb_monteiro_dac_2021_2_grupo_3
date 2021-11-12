import Vue from "vue"
import Router from "vue-router"
import Home from "./views/Home"
import Profile from "./views/Profile"
import Telateste from "./views/Telateste"
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
        path:"/Telateste",
        component: Telateste
    }]
})
