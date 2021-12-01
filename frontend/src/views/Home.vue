<template>
  <div>
    <Nav-bar/>
    <Side-bar @click="openModelLogin" />
    <Login :style="isDisplay" @click="closeModalLogin"/>
    <RegisterUser :style="isDisplayRegister"/>
    <div class="container d-flex flex-wrap justify-content-center p-2">
        
        <div class="text-center border border-warning mb-3 p-2" style="background: white; width: 23%; border-radius: 5px" >
          <img style="height: 15vw;" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
           <!-- <img src="data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAAAUA
            AAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO
                9TXL0Y4OHwAAAABJRU5ErkJggg==" alt="Red dot" /> -->
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
      <b-pagination-nav
        v-model="currentPage"
        :number-of-pages="pages"
        base-url="#"
        first-number
        align="center"
      ></b-pagination-nav>
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
        isDisplayRegister: "display: none;",
        pages: 100,
        currentPage: 5
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