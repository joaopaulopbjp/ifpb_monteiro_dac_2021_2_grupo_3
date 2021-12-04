<template>
  <div>
    <Nav-bar/>
    <Side-bar/>
    <div class="container d-flex justify-content-between mt-2 mb-5">
        <div class="container d-grid justify-content-start w-75 h-100">
            <div class="d-grid justify-content-start rounded p-4" style="background-color: white;border-radius: 1px solid black;">
                <b-form-checkbox>Select all</b-form-checkbox>
            </div>
            <div id="itemsDiv" class="container rounded mt-2 p-3" style="background-color: white;border-radius: 1px solid black;">
                </div>
            </div>
        </div>
        <div class="container d-grid w-25 rounded-bottom sticky" id="containerValue">
             
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

    .buttonMinus,.buttonPlus{
        width: 2vw;
    }
    .buttonPlus{
        background-color: #FCB13A;
        border: none;
        color:white;
    }
    .inputMeio{
        border: 2px solid #FCB13A;
        width: 3vw;
    }
    .buttonMinus{
        background-color: #FCB13A;
        border: none;
        color:white;
    }
    .inputMeio:focus{
        outline: none;

    }
    .inputMeio::-webkit-inner-spin-button{
        -webkit-appearance: none;
        margin: 0;
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
        }
    },
    mounted() {
        shoppingCartService.renderItens();
        this.isAdmin();
    }
}
</script>