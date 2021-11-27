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

                    <select name="companys" id="companyInput" style="width: 45%" class="ml-2">
                        <option value="">saraiva</option>
                    </select>

                    <b-form-file accept="image/jpeg, image/png" id="imageInput" style="width: 45%" class="ml-2 mr-3"></b-form-file>
                    
                </div>
                <div class="d-flex justify-content-between mb-4">
                    <b-card  class="align-baseline ml-3 mr-3 w-45 p-3">
                        <h4 class="">Authors</h4>
                        <div id="authorsOptions">
                        </div>
                    </b-card>

                    <b-form-textarea placeholder="Description" id="descriptionInput" class="md-textarea p-3" style="width: 45%; "></b-form-textarea>

                    <b-card class="ml-3 mr-3 w-45 p-3">
                        <h4>Categories</h4>
                        <div id="categoriesOptions">

                        </div>
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

var pageNumberAuthor = 0;
var pageNumberCategories = 0;

export default {
    name: "RegisterProduct",
    components: {NavBar, Footer, SideBar},
    data() {
      return {
        
      }
    },
    methods: {
        save() {
            productApi.saveEvent();
        },
        setAuthorOnVue(pageNumber) {
            let element = document.getElementById("authorsOptions");
            let html = element.innerHTML;
            fetch(`http://localhost:8080/api/author/find/find-all/${pageNumber}`, {
                method: 'GET',
                headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                }
            }).then(async function(apiResponse) {
                let json = await apiResponse.json();
                let count = 0;
                while (json[count] !== undefined) {
                    html = html + `<input type="checkbox" value="${json[count]["id"]}" id="authorCheckBox"> ${json[count]["name"]}<br>`
                    count++;
                }
                element.innerHTML = html;
            });
        },
        setCategoriesOnVue(pageNumber) {
            let element = document.getElementById("categoriesOptions");
            let html = element.innerHTML;
            fetch(`http://localhost:8080/api/category/find/find-all/${pageNumber}`, {
                method: 'GET',
                headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                }
            }).then(async function(apiResponse) {
                let json = await apiResponse.json();
                let count = 0;
                while (json[count] !== undefined) {
                    html = html + `<input type="checkbox" value="${json[count]["id"]}" id="categoryCheckBox"> ${json[count]["name"]}<br>`
                    count++;
                }
                element.innerHTML = html;
            });
        }
    },
    mounted() {
        this.save();
        this.setAuthorOnVue(pageNumberAuthor);
        this.setCategoriesOnVue(pageNumberCategories);
    }
}
</script>

<style scoped>
    #categorie,#authors{
        overflow-x: hidden;
        overflow-y: auto;
    }
    #companys{
        border: none;
        outline: 0;
        background: transparent;
        border-image: none;
        outline-offset: -2px;
        border-color: transparent;
        outline-color: transparent;
        box-shadow: none;
    }
    li{
        list-style-type: none;
    }
</style>