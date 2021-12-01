<template>
    <div>
        <Nav-bar/>
        <Side-bar/>
        <Register-author :style="isDisplayAuthor" @click="closeModalRegisAuthor"/>
        <Register-category :style="isDisplayCategory" @click="closeModalRegisCategory"/>
        <Register-company :style="isDisplayCompany" @click="closeModalRegisCompany"/>
        <Update-Author :style="isDisplayUpdateAuthor" @click="closeModalUpdateAuthor"/>
        <Update-category :style="isDisplayUpdateCategory" @click="closeModalUpdateCategory"/>
        <Update-Company :style="isDisplayUpdateCompany" @click="closeModalUpdateCompany"/>
        <Delete-author :style="isDisplayDeleteAuthor" @click="closeModalDeleteAuthor"/>
        <Delete-category :style="isDisplayDeleteCategory" @click="closeModalDeleteCategory"/>
        <Delete-Company :style="isDisplayDeleteCompany" @click="closeModalDeleteCompany"/>
        <div class="container mt-2 mb-5">
            <b-card v-if="isAdmin()">
                <div class="d-flex justify-content-between mb-4">
                    <h4><i class="fas fa-user-cog"></i> Workspace ADM</h4>
                </div>
                <div class="d-flex justify-content-start mb-4">
                    <div>
                        <h6>Target Product</h6>
                        <b-button-group vertical>
                            <b-button to="RegisterProduct" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-plus-circle"></i> Register Product
                            </b-button>
                            <b-button pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-trash-alt"></i> Delete Product
                            </b-button>
                            <b-button pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-edit"></i> Update Product
                            </b-button>
                        </b-button-group>
                    </div>
                    <div class="ml-3">
                        <h6>Target Author</h6>
                        <b-button-group vertical>
                            <b-button @click="openModalRegisAuthor()" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-plus-circle"></i> Register Author
                            </b-button>
                            <b-button @click="openModalDeleteAuthor()" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-trash-alt"></i> Delete Author
                            </b-button>
                            <b-button @click="openModalUpdateAuthor()" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-edit"></i> Update Author
                            </b-button>
                        </b-button-group>
                    </div>
                    <div class="ml-3">
                        <h6>Target Category</h6>
                        <b-button-group vertical>
                            <b-button @click="openModalRegisCategory()" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-plus-circle"></i> Register Category
                            </b-button>
                            <b-button @click="openModalDeleteCategory()" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-trash-alt"></i> Delete Category
                            </b-button>
                            <b-button @click="openModalUpdateCategory()" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-edit"></i> Update Category
                            </b-button>
                        </b-button-group>
                    </div>
                    <div class="ml-3">
                        <h6>Target Company</h6>
                        <b-button-group vertical>
                            <b-button @click="openModalRegisCompany()" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-plus-circle"></i> Register Company
                            </b-button>
                            <b-button @click="openModalDeleteCompany()" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-trash-alt"></i> Delete Company
                            </b-button>
                            <b-button @click="openModalUpdateCompany()" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-edit"></i> Update Company
                            </b-button>
                        </b-button-group>
                    </div>
                </div>
            </b-card>
        </div>
        <div class="container mt-2 mb-5">
            <b-card>
                <div class="d-flex justify-content-between mb-4">
                    <h4><i class="fas fa-user-circle"></i> Overview</h4>
                    <b-button variant="warning" class="my-2 my-sm-0" id="editButton" value="disabled" type="submit">
                    <i class="fas fa-pen"></i>
                    </b-button>
                </div>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-input type="text" style="width: 400px" id="usernameInput" disabled></b-form-input>
                    <b-form-input class="ml-4" type="text" style="width: 500px" id="emailInput" disabled></b-form-input>
                </div>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-input type="password" placeholder="Old password" style="width: 250px" id="oldPasswordInput" disabled></b-form-input>
                    <b-form-input class="ml-4" type="password" placeholder="New password" id="newPasswordInput" style="width: 250px" disabled></b-form-input>
                    <b-form-input class="ml-4" type="password" placeholder="Confirm password" style="width: 250px" id="confirmNewPasswordInput" disabled></b-form-input>
                </div>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-file accept="image/jpeg, image/png" id="imageInput" style="width: 25%" class=" mr-3" disabled></b-form-file>
                </div>
                <b-button variant="outline-success" class="my-2 my-sm-0" id="saveButton" type="submit">
                  <i class="far fa-save"></i> Save
                </b-button>
            </b-card>
        </div>
        <div class="container mt-2 mb-5">
            <b-card>
                <div class="d-flex justify-content-between mb-4">
                    <h4><i class="fas fa-shopping-basket"></i> Requests</h4>
                </div>
                <div class="d-flex justify-content-start mb-4">
                    <router-link class="text-decoration-none text-dark" to="">
                        <b-card class="border border-dark rounded text-center" style="background-color: #D1B1E8; width: 250px;">
                            <h5>A garota do lago</h5>
                            <img class="mt-2 mb-2" style="max-width: 120px" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
                            <h5>R$: 17,90</h5>
                        </b-card>
                    </router-link>
                    <router-link class="text-decoration-none text-dark" to="">
                        <b-card class="border border-dark rounded ml-3 text-center" style="background-color: #D1B1E8; width: 250px;">
                            <h5>A garota do lago</h5>
                            <img class="mt-2 mb-2" style="max-width: 120px" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
                            <h5>R$: 17,90</h5>
                        </b-card>
                    </router-link>
                    
                </div>
            </b-card>
        </div>
        <div class="container mt-2 mb-2">
            <b-card>
                <div class="d-flex justify-content-between mb-4">
                    <h4><i class="fas fa-map-marker-alt"></i> Address</h4>
                    <b-button variant="warning" class="my-2 my-sm-0" type="submit">
                    <i class="fas fa-pen"></i> Add new address
                    </b-button>
                </div>
                <div class="d-flex justify-content-start mb-4">
                        <b-card class="border border-dark rounded text-center" style="background-color: #D1B1E8; width: 280px;">
                            <b-form-input class="col-sm-12 form-control-plaintext" type="text" placeholder="Av principal Nº 12"></b-form-input>
                            <b-form-input class="col-sm-12 form-control-plaintext" type="text" placeholder="centro" ></b-form-input>
                            <b-form-input class="col-sm-12 form-control-plaintext" type="text" placeholder="monteiro" ></b-form-input>
                            <b-form-input class="col-sm-12 form-control-plaintext" type="text" placeholder="58540-123" ></b-form-input>
                            <b-form-input class="sm-12 form-control-plaintext" type="text" placeholder="paraiba" ></b-form-input>
                            <div class="d-flex justify-content-end mt-2">
                                <b-button variant="outline-danger" class="my-2 my-sm-0 mr-2" type="submit">
                                    <i class="fas fa-times"></i>
                                </b-button>
                                <b-button variant="outline-dark" class="my-2 my-sm-0 mr-2" type="submit">
                                    <i class="fas fa-pen"></i>
                                </b-button>
                                <b-button variant="outline-dark" class="my-2 my-sm-0 mr-2" type="submit">
                                    <i class="fas fa-trash-alt"></i>
                                </b-button>
                                <b-button variant="success" class="my-2 my-sm-0 mr-2" type="submit">
                                    <i class="far fa-save"></i> Save
                                </b-button>
                            </div>
                        </b-card>
                        <b-card class="border border-dark rounded text-center ml-3" style="background-color: #D1B1E8; width: 280px;">
                            <b-form-input class="col-sm-12 form-control-plaintext" type="text" placeholder="Av principal Nº 12"></b-form-input>
                            <b-form-input class="col-sm-12 form-control-plaintext" type="text" placeholder="centro" ></b-form-input>
                            <b-form-input class="col-sm-12 form-control-plaintext" type="text" placeholder="monteiro" ></b-form-input>
                            <b-form-input class="col-sm-12 form-control-plaintext" type="text" placeholder="58540-123" ></b-form-input>
                            <b-form-input class="sm-12 form-control-plaintext" type="text" placeholder="paraiba" ></b-form-input>
                            <div class="d-flex justify-content-end mt-2">
                                <b-button variant="outline-danger" class="my-2 my-sm-0 mr-2" type="submit">
                                    <i class="fas fa-times"></i>
                                </b-button>
                                <b-button variant="outline-dark" class="my-2 my-sm-0 mr-2" type="submit">
                                    <i class="fas fa-pen"></i>
                                </b-button>
                                <b-button variant="outline-dark" class="my-2 my-sm-0 mr-2" type="submit">
                                    <i class="fas fa-trash-alt"></i>
                                </b-button>
                                <b-button variant="success" class="my-2 my-sm-0 mr-2" type="submit">
                                    <i class="far fa-save"></i> Save
                                </b-button>
                            </div>
                        </b-card>
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
import RegisterAuthor from "../modal/RegisterAuthor.vue";
import RegisterCompany from "../modal/RegisterCompany.vue";
import RegisterCategory from '../modal/RegisterCategory.vue';
import UpdateAuthor from '../modal/UpdateAuthor.vue';
import UpdateCategory from '../modal/UpdateCategory.vue';
import UpdateCompany from '../modal/UpdateCompany.vue';
import DeleteAuthor from '../modal/DeleteAuthor.vue';
import DeleteCategory from '../modal/DeleteCategory.vue';
import DeleteCompany from '../modal/DeleteCompany.vue';

import { ProfileApi } from "../service/compiled/profile/ProfileApi.js";

let profileApi = new ProfileApi();

export default {
    name: "Profile",
    components: { NavBar, Footer, SideBar, 
    RegisterAuthor, RegisterCategory, RegisterCompany, UpdateAuthor, UpdateCategory, UpdateCompany,
    DeleteAuthor, DeleteCategory, DeleteCompany},
    mounted() {
        
        this.setInfoOnVue()
        profileApi.editButtonEvent();
        profileApi.saveButtonEvent();
    },
    beforeMount() {
        this.loggedVerify();
    },
    data() {
        return {
            isDisplayAuthor:"display: none;",
            isDisplayCategory:"display: none;",
            isDisplayCompany:"display: none;",
            isDisplayUpdateAuthor:"display: none;",
            isDisplayUpdateCategory:"display: none;",
            isDisplayUpdateCompany:"display: none;",
            isDisplayDeleteAuthor:"display: none;",
            isDisplayDeleteCategory:"display: none;",
            isDisplayDeleteCompany:"display: none;",
        }
    },
    methods: {
        isAdmin() {
            return profileApi.isAdmin();
        },
        setInfoOnVue() {
            profileApi.setInfoOnVue();
        },
        loggedVerify() {
            let usernameLocal = window.localStorage.getItem("username");
            if(usernameLocal === null || usernameLocal === ""){
                window.location.replace("/")
            }
        },
        closeModalRegisAuthor(){
            this.isDisplayAuthor = "display: none;"
        },
        openModalRegisAuthor() {
            this.isDisplayAuthor = "display: flex;"
        },
        closeModalRegisCategory(){
            this.isDisplayCategory = "display: none;"
        },
        openModalRegisCategory() {
            this.isDisplayCategory = "display: flex;"
        },
        closeModalRegisCompany(){
            this.isDisplayCompany = "display: none;"
        },
        openModalRegisCompany() {
            this.isDisplayCompany = "display: flex;"
        },
        closeModalUpdateAuthor(){
            this.isDisplayUpdateAuthor = "display: none;"
        },
        openModalUpdateAuthor() {
            this.isDisplayUpdateAuthor = "display: flex;"
        }, 
        closeModalUpdateCategory(){
            this.isDisplayUpdateCategory = "display: none;"
        },
        openModalUpdateCategory() {
            this.isDisplayUpdateCategory = "display: flex;"
        }, 
        closeModalUpdateCompany(){
            this.isDisplayUpdateCompany = "display: none;"
        },
        openModalUpdateCompany() {
            this.isDisplayUpdateCompany = "display: flex;"
        }, 
        closeModalDeleteAuthor(){
            this.isDisplayDeleteAuthor = "display: none;"
        },
        openModalDeleteAuthor() {
            this.isDisplayDeleteAuthor = "display: flex;"
        },
        closeModalDeleteCategory(){
            this.isDisplayDeleteCategory = "display: none;"
        },
        openModalDeleteCategory() {
            this.isDisplayDeleteCategory = "display: flex;"
        },
        closeModalDeleteCompany(){
            this.isDisplayDeleteCompany = "display: none;"
        },
        openModalDeleteCompany() {
            this.isDisplayDeleteCompany = "display: flex;"
        }
    }
}
</script>

<style scoped>

</style>