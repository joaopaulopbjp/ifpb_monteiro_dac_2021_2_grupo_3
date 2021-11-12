<template>
    <div class="profile">
        <Nav-bar/>
        <h3>Olá mundo</h3>
        <Footer/>
    </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'

export default {
    name: "Profile",
    components: {NavBar, Footer}
}
</script>