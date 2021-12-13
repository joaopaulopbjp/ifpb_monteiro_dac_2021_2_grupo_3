<template >
  <div>
    <Nav-bar/>
    <div id="items-wrapper container" class="mb-5">
          <div id="items"  @wheel="carouselShow" >
                  <div class="item"><img src="../image/carousel/slide1.png" alt=""></div>
                  <div class="item"><img src="../image/carousel/slide2.png" alt=""></div>
                  <div class="item"><img src="../image/carousel/slide3.png" alt=""></div>
                  <div class="item"><img src="../image/carousel/slide4.png" alt=""></div>
          </div>
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
        perPage: 1,
        qtdItems: 4,
        i : 0,
        moviment: 0
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
      carouselShow() {
          document.querySelector("#items").addEventListener("wheel", event => {
          /* if(event.deltaY > 0) {
            event.target.scrollBy(300,0);
          }else{
            event.target.scrollBy(-300,0);
          } */
          setInterval(() => {
                
                if(this.i !== this.qtdItems){
                   this.moviment += 300;
                   event.target.scrollBy(this.moviment,0);
                   this.i++;
                }else{
                  event.target.scrollBy(-this.moviment,0);
                  this.moviment = 0;
                  this.i = 0;
                }
          }, 2000)
          });
       }
    },

}

</script>

<style scoped>
 
 * {
   margin: 0;
   padding: 0;
   box-sizing: border-box;
 }

 #items-wrapper {
   width: 100vw;
 }

#items {
   display: flex;
   overflow-x: auto;
   scroll-snap-type: x mandatory;
   -webkit-overflow-scrolling: touch;
   scroll-behavior: smooth;
   
}

.item {
  flex: none;

  width: 100%;
  height: 600px;
  scroll-snap-align: start;
  pointer-events: none;
}

 img {
   width: 100%;
   height: 100%;
   object-fit: cover;
 }

 ::-webkit-scrollbar
{
    width: 0px;
}
::-webkit-scrollbar-track-piece
{
    background-color: transparent;
    -webkit-border-radius: 6px;
}

</style>