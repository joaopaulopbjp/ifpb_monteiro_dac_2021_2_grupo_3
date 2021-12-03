<template>
  <div>
    <Nav-bar/>
    <Side-bar/>
    <div class="container d-flex justify-content-between mt-2 mb-5">
        <div class="container d-grid justify-content-start w-75 h-100">
            <div class="d-grid justify-content-start rounded p-4" style="background-color: white;border-radius: 1px solid black;">
                <b-form-checkbox>Select all</b-form-checkbox>
            </div>
            <div id="itemsDiv">
                <!-- <div class="container rounded mt-2 p-3" style="background-color: white;border-radius: 1px solid black;">
                    <div class="d-flex justify-content-between">
                        <input type="checkbox" name="" id="" class="fa-2x">
                        <button variant="outline-dark" class="border-0 fa-2x" style="background-color: rgba(0, 0, 0, 0);border: none;"><i class="far fa-trash-alt w-100 h-100"></i></button>
                    </div>
                    <div class="d-flex justify-content-between">
                        <div class="d-flex justify-content-between">
                            <button class="text-decoration-none text-dark " style="background-color: rgba(0, 0, 0, 0);border:none;">
                                <img class="" style="max-width: 120px" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
                            </button>
                            <div class="d-grid ml-4">
                                <h4>A garota do lago</h4>
                                <h6>Author: Charlie Donlea</h6>
                                <div class="row justify-content-center mt-5 w-10 rounded" style="background-color: #9652C6;color: white;align-items: center;">
                                    <h5>R$: 17,90</h5>
                                </div>
                            </div>
                        </div>
                        <b-form-spinbutton class="mt-4" id="sb-inline" v-model="quantidade" inline style="background-color: #FCB13A;"></b-form-spinbutton>
                    </div>
                </div> -->

            </div>
        </div>
        <div class="container d-grid w-25 rounded-bottom sticky" id="containerValue">
            <!-- <div class="row rounded-top w-100 mb-0 pl-4 pt-3" style="background-color: white;">
                <div class="">
                    <h4>Order:</h4>
                    <h5>Subtotal: R$: 0,0</h5>
                    <h5>Transport: R$: 0,0</h5>
                    <hr class="ml-5 w-50">
                    <h5>Total: R$: 0,0</h5>
                </div>
            </div>
            <button class="row d-flex justify-content-center rounded-bottom w-100" style="background-color: #FCB13A;border: none;color: white;">buy</button> -->
        </div>
    </div>
    <Footer/>
  </div>
</template>

<style>
    .sticky {
        position: sticky;
        top: 1%;
        width: 10px;
        height: 10px;
    }
</style>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import SideBar from '../components/SideBar.vue'

import { ShoppingCartService } from '../service/compiled/shoppingCart/ShoppingCartService';
let shoppingCartService = new ShoppingCartService();
import { ProfileApi } from "../service/compiled/profile/ProfileApi.js";
let profileApi = new ProfileApi();

export default {
    name: "ShoppingCart",
    components: { NavBar , SideBar, Footer},
    methods: {
        isAdmin() {
            if(profileApi.isAdmin() || ( window.localStorage.getItem("token") == "" ||  window.localStorage.getItem("token") == null || window.localStorage.getItem("token") == undefined)) {
                window.location.replace("/")
            }
        }
    },
    data(){
        return{
            compras:[
                { text: 'Lewis', value: 'Lewis' }
            ],
            quantidade: 1,
        }
    },
    mounted() {
        shoppingCartService.renderItens();
        this.isAdmin();
    }
}
</script>