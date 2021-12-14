<template >
  <div>
    <Nav-bar/>
    <div id="slider" class="mb-5">
          <figure>
                  <img src="../image/carousel/slide1.png" alt="">
                  <img src="../image/carousel/slide2.png" alt="">
                  <img src="../image/carousel/slide3.png" alt="">
                  <img src="../image/carousel/slide4.png" alt="">
                  <img src="../image/carousel/slide1.png" alt="">
          </figure>
    </div>
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
      },
    },

}

</script>

<style scoped>
 
#slider {
  overflow: hidden;
}

#slider figure {
    position: relative;
    width: 500%;
    margin: 0;
    left: 0;
    animation:  20s slider infinite;
}

#slider figure img {
  width: 20%;
  height: 80vh;
  float: left;
}

@keyframes slider {
      0% {
            left: 0;
      }
      20% {
            left: 0;
      }
      25% {
            left: -100%;
      }
      45% {
            left: -100%;
      }
      50% {
            left: -200%;
      }
      70% {
            left: -200%;
      }
      75% {
            left: -300%;
      }
      95% {
            left: -300%;
      }
      100% {
            left: -400%;
      }
}

</style>