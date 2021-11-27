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

                    <select id="companyInput" style="width: 45%" class="ml-2 companys">
                        <option selected disabled value="">Select a company</option>
                    </select>

                    <b-form-file accept="image/jpeg, image/png" id="imageInput" style="width: 45%" class="ml-2 mr-3"></b-form-file>
                    
                </div>
                <div class="d-flex justify-content-between mb-4">
                    <b-card  class="align-baseline ml-3 mr-3 w-45 p-3">
                        <h4 class="">Authors</h4>
                        <div class="checkboxWidth" id="authorsOptions">
                        </div>
                    </b-card>

                    <b-form-textarea placeholder="Description" id="descriptionInput" class="md-textarea p-3" style="width: 45%; "></b-form-textarea>

                    <b-card class="ml-3 mr-3 w-45 p-3">
                        <h4>Categories</h4>
                        <div class="checkboxWidth" id="categoriesOptions">
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
        },
        setCompaniesOnVue() {
            let element = document.getElementById("companyInput");
            let html = element.innerHTML;
            fetch(`http://localhost:8080/api/company/find-all`, {
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
                    html = html + `<option value="${json[count]["id"]}">${json[count]["name"]}</option>`
                    count++;
                }
                element.innerHTML = html;
            });
        },
        authorScrollPage() {
            let authorOp = document.getElementById("authorsOptions");
            authorOp.addEventListener("scroll", () => {
                if(authorOp.scrollTop + authorOp.clientHeight == authorOp.scrollHeight) {
                    pageNumberAuthor++;
                    this.setAuthorOnVue(pageNumberAuthor);
                }
            })
        },
        categoryScrollPage() {
            let categoryOp = document.getElementById("categoriesOptions");
            categoryOp.addEventListener("scroll", () => {
                if(categoryOp.scrollTop + categoryOp.clientHeight == categoryOp.scrollHeight) {
                    pageNumberCategories++;
                    this.setCategoriesOnVue(pageNumberCategories);
                }
            })
        }
    },
    mounted() {
        this.save();
        this.authorScrollPage();
        this.categoryScrollPage();
        this.setAuthorOnVue(pageNumberAuthor);
        this.setCompaniesOnVue();
        this.setCategoriesOnVue(pageNumberCategories);
    }
}
</script>

<style scoped>
    .checkboxWidth {
        height: 15vh;
        width: 15vw;
    }

    #categoriesOptions,#authorsOptions{
        overflow-x: hidden;
        overflow-y: auto;
    }

    .companys{
        color: gray;
        padding-left: 0.5vw;
        border-color: rgba(146, 146, 146, 0.507);
        border-radius: 5px;
    }
</style>