<template>
  <div>
    <Nav-bar/>
    <Side-bar @click="openModelHome" />
    <Login :style="isDisplay" @click="closeModalHome"/>
    <br>
    <Footer/>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import SideBar from '../components/SideBar.vue'
import Login from '../components/Login.vue'

export default {
    name: "Home",
    components: { NavBar , SideBar, Footer, Login},
    data() {
      return {
        isDisplay: "display: none;"
      }
    },
    methods: {
      closeModalHome(){
        this.isDisplay = "display: none;"
      },
      openModelHome() {
        this.isDisplay = "display: flex;"
      }
    },
}
</script>