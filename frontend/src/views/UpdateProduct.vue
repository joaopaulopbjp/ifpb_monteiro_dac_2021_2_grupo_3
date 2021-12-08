<template>
    <div class="updateProduct">
        <Nav-bar/>
        <Side-bar/>
        <div class="container mt-2 mb-5">
            <b-card>
                <div class="d-flex justify-content-between ml-5"><h1>Update a Book</h1></div>
                <b-card class="align-items-center">
                    <select id="selectProductOptions" class="p-2" style="width: 30vw">
                    </select>
                </b-card>
                <b-card class="mt-4">
                    <div class="d-flex justify-content-start mb-4">
                        <b-form-input type="text" placeholder="New Title" id="newTitleInput" style="width: 65%" class="ml-3"></b-form-input>
                        <b-form-input type="number" placeholder="New YearLaunch" id="newYearLaunchInput" style="width: 40%" class="ml-2"></b-form-input>
                        <b-form-input type="number" placeholder="New Page" id="newPageInput" style="width: 40%" class="ml-2 mr-3"></b-form-input>
                    </div>
                    <div class="d-flex justify-content-start mb-4">
                        <b-form-input type="number" placeholder="New Price" id="newPriceInput" style="width: 30%" class="ml-3"></b-form-input>
                        <b-form-input type="number" placeholder="New Inventory" id="newInventoryInput" style="width: 40%" class="ml-2"></b-form-input>

                        <select id="newCompanyInput" style="width: 45%" class="ml-2 companys">
                            <option selected disabled value="">Select New a company</option>
                        </select>

                        <b-form-file accept="image/jpeg, image/png" id="newimageInput" style="width: 45%" class="ml-2 mr-3"></b-form-file>
                        
                    </div>
                    <div class="d-flex justify-content-between mb-4">
                        <b-card  class="ml-3 mr-3 w-45 p-3">
                            <h4 class="text-center">Authors</h4>
                            <div class="checkboxWidth" id="newAuthorsOptions">
                            </div>
                        </b-card>

                        <b-form-textarea placeholder="New Description" id="newDescriptionInput" class="md-textarea p-3" style="width: 45%; "></b-form-textarea>

                        <b-card class="ml-3 mr-3 w-45 p-3">
                            <h4 class="text-center">Categories</h4>
                            <div class="checkboxWidth" id="newCategoriesOptions">
                            </div>
                        </b-card>
                    </div>
                    <div class="d-flex justify-content-center">
                        <b-button to="Profile" variant="danger" class="mt-4 mr-2">Cancel</b-button>
                        <b-button variant="warning" class="mt-4" id="buttonUpdateProductView">Update Book</b-button>
                    </div>
                </b-card>

            </b-card>
        </div>
        <Footer/>
    </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import SideBar from '@/components/SideBar.vue'

var pageNumberProducts = 0;
import { ProductApi } from "../service/compiled/product/ProductApi";
let productApi = new ProductApi();

export default {
    name: "UpdateProduct",
    components: {NavBar, Footer, SideBar},
    data() {
      return {
        
      }
    },

    methods: {
        setProductOnVue(pageNumber) {
            let element = document.getElementById("selectProductOptions");
            let html = element.innerHTML;
            fetch(`http://localhost:8080/api/book/find/find-all-available/${pageNumber}`, {
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
                    html = html + `<option  value="${json[count]["id"]}" id="productOptionUpdate"> ${json[count]["title"]}</option>`
                    count++;
                }
                element.innerHTML = html;
            });
        },

        productScrollPage() {
            let productOp = document.getElementById("selectProductOptions");
            productOp.addEventListener("scroll", () => {
                if(productOp.scrollTop + productOp.clientHeight == productOp.scrollHeight) {
                    pageNumberProducts++;
                    this.setCategoriesOnVue(pageNumberProducts);
                }
            })
        }
    },

    mounted() {
        this.setProductOnVue(0);
        this.productScrollPage();
        productApi.updateProductListener();
    },
    
}
</script>

<style scoped>
    .checkboxWidth {
        height: 15vh;
        width: 15vw;
    }

    #newCategoriesOptions,#newAuthorsOptions{
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