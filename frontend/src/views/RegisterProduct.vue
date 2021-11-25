<template>
    <div class="registerProduct">
        <Nav-bar/>
        <Side-bar/>
        <div class="container mt-2 mb-5">
            <b-card>
                <div class="d-flex justify-content-between ml-5"><h1>Register a Book</h1></div>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-input type="text" placeholder="Title" id="titleInput" style="width: 65%" class="ml-3"></b-form-input>
                    <b-form-input type="number" placeholder="YearLaunch" id="yearLaunchInput" style="width: 40%" class="ml-2"></b-form-input>
                    <b-form-input type="number" placeholder="Page" id="pageInput" style="width: 40%" class="ml-2 mr-3"></b-form-input>
                </div>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-input type="number" placeholder="Price" id="priceInput" style="width: 30%" class="ml-3"></b-form-input>
                    <b-form-input type="number" placeholder="Inventory" id="inventoryInput" style="width: 40%" class="ml-2"></b-form-input>

                    <b-form-select v-model="campanySelected" :options="companys" id="companyInput" style="width: 45%" class="ml-2"></b-form-select>

                    <b-form-file accept="image/jpeg, image/png" v-model="File" id="imageInput" style="width: 45%" class="ml-2 mr-3"></b-form-file>
                    
                </div>
                <div class="d-flex justify-content-between mb-4">
                    <b-card class="ml-3 mr-3 w-45 p-3">
                        <h4>Authors</h4>
                        
                        <b-form-checkbox-group id="authorsInput"
                        v-model="selected"
                        :options="authors"
                        :aria-describedby="ariaDescribedby"
                        name="flavour-1a"
                        stacked
                        ></b-form-checkbox-group>
                    </b-card>

                    <b-form-textarea v-model="text" placeholder="Description" id="descriptionInput" class="md-textarea p-3" style="width: 45%; "></b-form-textarea>

                    <b-card class="ml-3 mr-3 w-45 p-3">
                        <h4>Categories</h4>
                        <b-form-checkbox-group id="categoriesInput"
                        v-model="selected"
                        :options="categories"
                        :aria-describedby="ariaDescribedby"
                        stacked></b-form-checkbox-group>
                    </b-card>
                </div>
                <div class="d-flex justify-content-center">
                    <b-button to="Profile" variant="danger" class="mt-4 mr-2">Cancel</b-button>
                    <b-button variant="warning" class="mt-4" id="saveButton">Register Book</b-button>
                </div>
            </b-card>
        </div>
        <Footer/>
    </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import SideBar from '@/components/SideBar.vue'

import { ProductApi } from "../service/compiled/product/ProductApi.js";
let productApi = new ProductApi();

export default {
    name: "RegisterProduct",
    components: {NavBar, Footer,SideBar},
    
    data() {
      return {
        // parte que coloca os autores no b-card do authors
        authors: [
          { text: 'Lewis', value: '1'},
          { text: 'Clives', value: '2' },
        ],
        categories:[
            {text: "Adventure", value: "1"},
            {text: "Horror", value: "2"}
        ],
        companys: [
            {value: null, text: "Please select an company"},
            {value: "1", text: "saraiva"}
        ],
        campanySelected: null,
      }
    },
    methods: {
        save() {
            productApi.saveEvent();
        }
    },
    mounted() {
        this.save();
    }
}
</script>

<style scoped>
    
</style>