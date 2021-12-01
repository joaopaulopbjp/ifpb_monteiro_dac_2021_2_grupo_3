<template>
  <div>
    <Nav-bar/>
    <Side-bar @click="openModelLogin" />
    <Login :style="isDisplay" @click="closeModalLogin"/>
    <RegisterUser :style="isDisplayRegister"/>
    <div class="container d-flex flex-wrap justify-content-center p-2" id="books-div">
    </div>
    <div>
        <b-pagination id="navegation-pages"
        v-model="currentPageNumber"
        :total-rows="rows"
        :per-page="perPage"
        first-number
        align="center"
      ></b-pagination>
    </div>
    <Footer/>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import SideBar from '../components/SideBar.vue'
import Login from '../modal/Login.vue'
import RegisterUser from '../modal/RegisterUser.vue'

import { HomeService } from '../service/compiled/home/HomeService'
let homeService = new HomeService();

export default {
    name: "Home",
    components: { NavBar , SideBar, Footer, Login, RegisterUser},
    data() {
      return {
        isDisplay: "display: none;",
        isDisplayRegister: "display: none;",
        currentPageNumber: 1,
        rows: 0,
        perPage: 1
      }
    },
    mounted() {
      this.initPageSize();
      homeService.renderbook(0);
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
      },
      initPageSize() {
        homeService.getTotalPages().then(num => {
          this.rows = num;
          this.initPageNav();
        })
      },
      initPageNav() {
        document.getElementById("navegation-pages").addEventListener("click", () => {
          if(this.currentPageNumber <= this.rows) {
            homeService.renderbook(this.currentPageNumber - 1);
          }
        })
      }
    },
}
</script>