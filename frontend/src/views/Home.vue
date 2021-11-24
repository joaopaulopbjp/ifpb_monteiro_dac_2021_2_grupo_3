<template>
  <div>
    <Nav-bar/>
    <Side-bar @click="openModelLogin" />
    <Login :style="isDisplay" @click="closeModalLogin"/>
    <RegisterUser :style="isDisplayRegister"/>
    <br>
    <Footer/>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import SideBar from '../components/SideBar.vue'
import Login from '../modal/Login.vue'
import RegisterUser from '../modal/RegisterUser.vue'

export default {
    name: "Home",
    components: { NavBar , SideBar, Footer, Login, RegisterUser},
    data() {
      return {
        isDisplay: "display: none;",
        isDisplayRegister: "display: none;"
      }
    },
    methods: {
      closeModalLogin(){
        this.isDisplay = "display: none;"
      },
      openModelLogin() {
        this.isDisplay = "display: flex;"
      },
      closeModalRegister(){
        this.isDisplayRegister = "display: none;"
      },
      openModelRegister() {
        this.isDisplayRegister = "display: flex;"
      }
    },
}
</script>