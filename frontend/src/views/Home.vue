<template>
  <div>
    <Nav-bar/>
    <Side-bar @click="openModelLogin" />
    <Login :style="isDisplay" @click="closeModalLogin"/>
    <RegisterUser :style="isDisplayRegister"/>
    <div class="container row d-flex justify-content-center">
      <div class="container col d-flex justify-content-between">
        <div class="p-5" style="background-color: white;border-color: #FCB13A;">
            <img class="row w-25" src="https://www.thebookshop.pl/images/thebookshop/8000-9000/The-Complete-Chronicles-of-Narnia-by-C-S-Lewis_[8525]_568.jpg" alt="" style="margin: auto;">
            <p class="row" style="margin: auto;">As cronicas de Nárnia</p>
            <p class="row" style="margin: auto;">Author: Lewis, Clives, Staples</p>
            <b-form-rating class="row w-50 h-25" id="media" inline color="#FCB13A" value="4" style="border: none;margin: auto;"/>
            <div class="row " style="margin: auto;">
              <div class="col p-2" style="box-shadow: 15px 1px 10px black;background-color: #9652C6;color: white;border-radius: 10px 0px 0px 10px;">
                <h5 class="col" style="padding: auto;margin: auto;">R$ 37,90</h5>
              </div>
              <b-button class="col" style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
            </div>
        </div>
        
      </div>
      
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