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
            </b-card>
            <b-card id="paymentMethod" class="mt-4 p-3">
                <h4 style="font-weight: bold;">Payment Method</h4>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-input id="namePayment" class="" placeholder="Name"/>
                    <b-form-input id="cpfPayment" v-mask="'###.###.###-##'" v-model="maskCpf" class="ml-4" placeholder="CPF"/>
                </div>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-input id="ccnPayment" v-mask="'##### #### #### ####'" v-model="maskCCN" value="" type="text" class="" placeholder="Credit Card Number"/>
                    <b-form-input id="dataPayment" type="month" value="" class="ml-4" />
                    <b-form-input id="cvcPayment" type="number" value="" class="w-25 ml-4" placeholder="CVC"/>
                </div>
            </b-card>
            <b-card id="orderReview" class="mt-4 p-3">
                <h4 style="font-weight: bold;">Order review</h4>
                <div id="orderList" class="d-grid justify-content-between">
                </div>
                
            </b-card>
        </div>
        <div class="container d-grid w-25 mt-3 sticky">
            <div id="orderSummary" class="row w-100 mb-0 pl-4 pt-3" style="background-color: white;">
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

import { PaymentService } from '../service/compiled/payment/PaymentService';
let paymentService = new PaymentService();

export default {
    name: "Home",
    components: { NavBar , SideBar, Footer, Login, RegisterUser},
    data() {
      return {
        isDisplay: "display: none;",
        isDisplayRegister: "display: none;",
        maskCCN:'##### #### #### ####',
        maskCpf:'###.###.###-##',
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
    mounted() {
        paymentService.addAddressOnVue();
        paymentService.addOrderlistOnVue();
        paymentService.addCheckoutEvent();
    }
};

</script>

<style>
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