<template>
  <div>
    <Nav-bar/>
    <Side-bar/>
    <br>
    <Footer/>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import SideBar from '../components/SideBar.vue'

export default {
    name: "Home",
    components: { NavBar , SideBar, Footer}
}
</script>