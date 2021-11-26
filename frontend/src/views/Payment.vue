<template>
  <div>
    <Nav-bar/>
    <Side-bar @click="openModelLogin" />
    <Login :style="isDisplay" @click="closeModalLogin"/>
    <RegisterUser :style="isDisplayRegister"/>
    <div class="container d-flex justify-content-between mt-2 mb-5">
        <div class="container d-grid justify-content-start w-75 h-100">
            <b-card id="address" class="mt-3 p-3">
                <h4 style="font-weight: bold;">Shipping Information</h4>
                <div class="d-flex justify-content-between mt-5">
                    <div class="">
                        <h5 class="">Sicrano Beltrano Silva</h5>
                        <h5 class="mt-3">Av principal, 204</h5>
                        <h5>Centro</h5>
                        <h5>Monteiro, paraiba, Brasil, 58550-000</h5>
                    </div>
                    <div class="">
                        <b-button style="background-color: transparent;color: orange;border: none;">select another address</b-button>
                    </div>
                </div>
            </b-card>
            <b-card id="paymentMethod" class="mt-4 p-3">
                <h4 style="font-weight: bold;">Payment Method</h4>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-input id="namePayment" class="" placeholder="Name"/>
                    <b-form-input id="cpfPayment" class="ml-4" placeholder="CPF"/>
                </div>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-input id="ccnPayment" type="number" class="" placeholder="Credit Card Number"/>
                    <b-form-input id="dataPayment" type="month" class="ml-4" />
                    <b-form-input id="cvcPayment" type="number" class="w-25 ml-4" placeholder="CVC"/>
                </div>
            </b-card>
            <b-card id="orderReview" class="mt-4 p-3">
                <h4 style="font-weight: bold;">Order review</h4>
                <div class="d-grid justify-content-between">
                    <div class="d-flex justify-content-start m-3">
                        <router-link class="text-decoration-none text-dark" to="">
                            <img class="" style="max-width: 120px" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
                        </router-link>
                        <div class="d-grid ml-4">
                            <h4>A garota do lago</h4>
                            <h6>Author: Charlie Donlea</h6>
                            <h6>Amount order: 1</h6>
                            <div class="row justify-content-center mt-5 w-10 rounded" style="background-color: #9652C6;color: white;align-items: center;">
                                <h5>R$: 17,90</h5>
                            </div>
                        </div>
                    </div>
                    <div class="d-flex justify-content-start m-3">
                        <router-link class="text-decoration-none text-dark" to="">
                            <img class="" style="max-width: 120px" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
                        </router-link>
                        <div class="d-grid ml-4">
                            <h4>A garota do lago</h4>
                            <h6>Author: Charlie Donlea</h6>
                            <h6>Amount order: 1</h6>
                            <div class="row justify-content-center mt-5 w-10 rounded" style="background-color: #9652C6;color: white;align-items: center;">
                                <h5>R$: 17,90</h5>
                            </div>
                        </div>
                    </div>
                </div>
                
            </b-card>
        </div>
        <div class="container d-grid w-25 mt-3 sticky">
            <div id="orderSummary" class="row w-100 mb-0 pl-4 pt-3" style="background-color: white;">
                <div class="">
                    <h3 class="p-2">Order Summary</h3>
                    <h5>value: R$: 0,0</h5>
                    <h5>shipping: R$: 0,0</h5>
                    <hr class="ml-4 w-75">
                    <h5>Total: R$: 0,0</h5>
                </div>
            </div>
            <button id="checkout" class="row d-flex justify-content-center w-100">Checkout</button>
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

<style scoped>
    #address,#paymentMethod,#orderReview{
        border-radius: 25px;
    }

    #namePayment,#cpfPayment,#ccnPayment,#dataPayment,#cvcPayment{
        background: linear-gradient(180deg, #9652C6 33.85%, rgba(150, 82, 198, 0.67) 85.94%), #FFFFFF;
    }
    
    #namePayment::placeholder,#cpfPayment::placeholder,#ccnPayment::placeholder,#dataPayment,#cvcPayment::placeholder{
        color: black;
    }

    #orderSummary{
        border-radius: 20px 20px 0px 0px;
    }
    #checkout{
        background-color: #FCB13A;
        border: none;
        color: white;
        padding: 2vh;
        border-radius: 0px 0px 20px 20px;
    }
</style>