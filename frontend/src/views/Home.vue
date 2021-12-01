<template>
  <div>
    <Nav-bar/>
    <Side-bar @click="openModelLogin" />
    <Login :style="isDisplay" @click="closeModalLogin"/>
    <RegisterUser :style="isDisplayRegister"/>
    <div class="container d-flex flex-wrap justify-content-center p-2" id="books-div">
        
        <div class="text-center border border-warning mb-3 p-2" style="background: white; width: 23%; border-radius: 5px" >
          <img style="height: 15vw;" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
          <h6 class="mt-2">A garota do lago</h6>
          <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none; height: 5vh"/>
          <div class="d-flex justify-content-center" style="height: 8vh;">
            <b-button class="btn-sm" variant="dark" style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: 17,90</b-button>
            <b-button class="btn-sm " style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
          </div>
        </div>
        <div class="text-center border border-warning ml-3 mb-3 p-2" style="background: white; width: 23%; border-radius: 5px" >
          <img style="height: 15vw" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
          <h6 class="mt-2">A garota do lago</h6>
          <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none; height: 5vh"/>
          <div class="d-flex justify-content-center" style="height: 8vh;">
            <b-button class="btn-sm" variant="dark" style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: 17,90</b-button>
            <b-button class="btn-sm " style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
          </div>
        </div>
        <div class="text-center border border-warning ml-3 mb-3 p-2" style="background: white; width: 23%; border-radius: 5px" >
          <img style="height: 15vw" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
          <h6 class="mt-2">A garota do lago</h6>
          <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none; height: 5vh"/>
          <div class="d-flex justify-content-center" style="height: 8vh;">
            <b-button class="btn-sm" variant="dark" style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: 17,90</b-button>
            <b-button class="btn-sm " style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
          </div>
        </div>
        <div class="text-center border border-warning ml-3 mb-3 p-2" style="background: white; width: 23%; border-radius: 5px" >
          <img style="height: 15vw" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
          <h6 class="mt-2">A garota do lago</h6>
          <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none; height: 5vh"/>
          <div class="d-flex justify-content-center" style="height: 8vh;">
            <b-button class="btn-sm" variant="dark" style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: 17,90</b-button>
            <b-button class="btn-sm " style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
          </div>
        </div>
        <div class="text-center border border-warning mb-3 p-2" style="background: white; width: 23%; border-radius: 5px" >
          <img style="height: 15vw" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
          <h6 class="mt-2">A garota do lago</h6>
          <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none; height: 5vh"/>
          <div class="d-flex justify-content-center" style="height: 8vh;">
            <b-button class="btn-sm" variant="dark" style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: 17,90</b-button>
            <b-button class="btn-sm " style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
          </div>
        </div>
        <div class="text-center border border-warning ml-3 mb-3 p-2" style="background: white; width: 23%; border-radius: 5px" >
          <img style="height: 15vw" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
          <h6 class="mt-2">A garota do lago</h6>
          <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none; height: 5vh"/>
          <div class="d-flex justify-content-center" style="height: 8vh;">
            <b-button class="btn-sm" variant="dark" style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: 17,90</b-button>
            <b-button class="btn-sm " style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
          </div>
        </div>
        <div class="text-center border border-warning ml-3 mb-3 p-2" style="background: white; width: 23%; border-radius: 5px" >
          <img style="height: 15vw" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
          <h6 class="mt-2">A garota do lago</h6>
          <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none; height: 5vh"/>
          <div class="d-flex justify-content-center" style="height: 8vh;">
            <b-button class="btn-sm" variant="dark" style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: 17,90</b-button>
            <b-button class="btn-sm " style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
          </div>
        </div>
        <div class="text-center border border-warning ml-3 mb-3 p-2" style="background: white; width: 23%; border-radius: 5px" >
          <img style="height: 15vw" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
          <h6 class="mt-2">A garota do lago</h6>
          <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none; height: 5vh"/>
          <div class="d-flex justify-content-center" style="height: 8vh;">
            <b-button class="btn-sm" variant="dark" style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: 17,90</b-button>
            <b-button class="btn-sm " style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
          </div>
        </div>
        
  
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
        rows: 10,
        perPage: 1
      }
    },
    mounted() {
      this.initPageSize();
      this.initPageNav();
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