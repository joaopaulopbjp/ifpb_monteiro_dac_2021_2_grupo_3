<template>
  <div>
    <Nav-bar/>
    <Side-bar @click="openModelLogin" />
    <Login :style="isDisplay" @click="closeModalLogin"/>
    <RegisterUser :style="isDisplayRegister"/>
    <div class="container d-flex p-2">
        <b-card class="border border-warning rounded text-center ml-2" style="width: 25vw;">
          <img class="mt-2 mb-2" style="max-width: 120px" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
          <h6>A garota do lago</h6>
          <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none;"/>
          <div class="d-flex justify-content-center">
            <b-button style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: 17,90</b-button>
            <b-button style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
          </div>
        </b-card>
        <b-card class="border border-warning rounded text-center ml-2" style="width: 25vw;">
          <img class="mt-2 mb-2" style="max-width: 120px" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
          <h6>A garota do lago</h6>
          <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none;"/>
          <div class="d-flex justify-content-center">
            <b-button style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: 17,90</b-button>
            <b-button style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
          </div>
        </b-card>
        <b-card class="border border-warning rounded text-center ml-2" style="width: 25vw;">
          <img class="mt-2 mb-2" style="max-width: 120px" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
          <h6>A garota do lago</h6>
          <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none;"/>
          <div class="d-flex justify-content-center">
            <b-button style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: 17,90</b-button>
            <b-button style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
          </div>
        </b-card>
        <b-card class="border border-warning rounded text-center ml-2" style="width: 25vw;">
          <img class="mt-2 mb-2" style="max-width: 120px" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
          <h6>A garota do lago</h6>
          <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none;"/>
          <div class="d-flex justify-content-center">
            <b-button style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: 17,90</b-button>
            <b-button style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
          </div>
        </b-card>   
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