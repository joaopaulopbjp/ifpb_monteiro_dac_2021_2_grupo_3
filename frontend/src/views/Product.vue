<template>
    <div>
        <Nav-bar/>
        <Side-bar/>
        <div id="productInfo" class="container justify-content-center mt-2 mb-5">
            <div class="row" id="head" style="background-color: white;border:2px solid rgb(232, 232, 232)">
                <div id="titleInfoProduct" class="ml-5">
                </div>
            </div>
            <div class="row d-flex justify-content-between mt-3">
                <div class="col align-items-center text-center" id="imagemMediaProduct" style="background-color: white;border:2px solid rgb(232, 232, 232)">
                </div>
                <div class="col ml-5">
                    <div class="row justify-content-start p-3 h-75" id="pricesContent" style="background-color: white;border:2px solid rgb(232, 232, 232)">
                        <div class="col h-75">
                            <!-- <div class="row ml-5 mt-3">
                                <h4 class="" style="font-size: larger;color: gray;text-decoration: line-through;font-weight: bolder;">R$ {{desconto}}</h4>
                            </div> -->
                            <div class="row ml-4">
                                <div id="priceProduct" class="p-3" style="background-color: #9652C6;color: white;border-radius: 10px 0px 0px 10px;">
                                    
                                </div>
                            </div>
                            <div id="credicardProduct" class="ml-3 mt-3 mb-3 mt-5">
                                
                            </div>
                            <button class="col rounded p-1" style="background-color: #FCB13A;border: none;color: white;">ADD TO CART</button>
                        </div>
                    </div>
                    <div class="row p-2 mt-2" id="delivery" style="background-color: white;border:2px solid rgb(232, 232, 232)">
                        <span class="d-flex justify-content-center p-2" style="color: #FCB13A;align-items: center;">Calcule Delivery
                            <input type="text" class="w-50 ml-1 p-2" style="background: #ECECEC;border-radius: 10px 0px 0px 10px;border: none;">
                            <button class="p-2" style="background: #FCB13A;border-radius: 0px 10px 10px 0px; border: none;color: white;">Calculate</button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="row mt-3 p-4 d-flex flex-column" id="descriptionProduct" style="background-color: white;border:2px solid rgb(232, 232, 232)">
            </div>
            <div class="row mt-3 border border-5 p-4" id="evaluate" style="background-color: white;border:2px solid rgb(232, 232, 232)">
                <div class="col p-2">
                    <h4 class="row">Evaluate</h4>
                    <div id="evaluateProduct" class="col">
                
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
import SideBar from '@/components/SideBar.vue'

import { ProductApi } from '../service/compiled/product/ProductApi';
let productApi = new ProductApi();

export default {
    name: "Product",
    components: {NavBar, Footer, SideBar},

    mounted() {
        productApi.addProductInfoOnVue();
    }
}
</script>

<style>
    #head, #imagemMedia,#pricesContent,#delivery,#description,#characteristics,#evaluate{
        border-radius: 25px;
    }
    .checked {
        color: orange;
    }

</style>