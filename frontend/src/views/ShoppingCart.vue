<template>
  <div>
    <Nav-bar/>
    <Side-bar/>
    <div class="container d-flex justify-content-between mt-2 mb-5">
        <div class="container d-grid justify-content-start">
            <b-card class="d-grid justify-content-start rounded mr-5">
                <b-form-checkbox>Select all</b-form-checkbox>
            </b-card>
            <b-card class="d-flex flex-row rounded mt-2 mr-5">
                <div class="container d-flex justify-content-start ">
                    <b-form-checkbox></b-form-checkbox>
                    <router-link class="text-decoration-none text-dark " to="">
                        <img class="" style="max-width: 120px" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
                    </router-link>
                    <div class="d-grid ml-4">
                        <h4>A garota do lago</h4>
                        <h6>Author: Charlie Donlea</h6>
                        <b-card class="d-flex mt-5 w-10">
                            <h5>R$: 17,90</h5>
                        </b-card>
                    </div>
                </div>
                <div class="container d-flex justify-content-end">
                    <b-button variant="outline-dark"><i class="far fa-trash-alt"></i></b-button>
                </div>
            </b-card>
        </div>
        <div>
            <b-card class="d-flex justify-content-end mt-5">
                <h4>Order:</h4>
                Subtotal:
                Transport: 
                Total:
            </b-card>
            <b-button variant="warning">buy</b-button>
        </div>
    </div>
    <Footer/>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import SideBar from '../components/SideBar.vue'

export default {
    name: "ShoppingCart",
    components: { NavBar , SideBar, Footer},
    methods: {
        
    },
    data(){
        return{
            compras:[
                { text: 'Lewis', value: 'Lewis' }
            ]


        }
    }
}
</script>