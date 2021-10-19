<template>
  <div id="app">
    <Nav-bar/>
    <br>
    <Footer/>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'

export default {
  components: { NavBar , Footer }
}
</script>

<style>

* {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  padding: 0;
  margin: 0;
  /* box-sizing: border-box; */
}
</style>