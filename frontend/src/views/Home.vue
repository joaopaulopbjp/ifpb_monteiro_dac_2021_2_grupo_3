<template>
  <div>
    <Nav-bar/>
    <Side-bar :logged="logged" :isDisplay="isDisplay"/>
    <Login :style="isDisplay" />
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
        logged: false,
        isDisplay: "display: none;"
      }
    },
    methods: {
      closeModal(){
        return "display: none;"
      }
    },
}
</script>