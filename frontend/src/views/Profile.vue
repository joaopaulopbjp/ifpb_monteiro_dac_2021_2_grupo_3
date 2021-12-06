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
        <RegisterAddress :style="isDisplayRegisterAddress" @click="closeModalRegisterAddress"/>
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
                <b-button variant="outline-success" class="my-2 my-sm-0" id="saveButton" style="display: none" type="submit">
                  <i class="far fa-save"></i> Save
                </b-button>
            </b-card>
        </div>
        <div class="container mt-2 mb-5">
            <b-card>
                <div class="d-flex justify-content-between mb-4">
                    <h4><i class="fas fa-shopping-basket"></i> Requests</h4>
                </div>
                <div id="requestprofile" class="d-flex justify-content-start mb-4">
                </div>
            </b-card>
        </div>
        <div class="container mt-2 mb-2">
            <b-card>
                <div class="d-flex justify-content-between mb-4">
                    <h4><i class="fas fa-map-marker-alt"></i> Address</h4>
                    <b-button @click="openModalRegisterAddress()" variant="warning" class="my-2 my-sm-0" type="submit">
                    <i class="fas fa-pen"></i> Add new address
                    </b-button>
                </div>
                <div id="addressDiv" class="d-flex justify-content-start mb-4">
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
import RegisterAddress from '../modal/RegisterAddress.vue';

let profileApi = new ProfileApi();

export default {
    name: "Profile",
    components: { NavBar, Footer, SideBar, RegisterAuthor, RegisterCategory, RegisterCompany, UpdateAuthor, UpdateCategory, UpdateCompany, DeleteAuthor, DeleteCategory, DeleteCompany, RegisterAddress },
    mounted() {
        
        this.setInfoOnVue()
        profileApi.editButtonEvent();
        profileApi.saveButtonEvent();
        profileApi.addAddressOnVue();
        profileApi.addRequestOnVue();
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
            isDisplayRegisterAddress:"display: none;",
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
        },
        closeModalRegisterAddress(){
            this.isDisplayRegisterAddress = "display: none;"
        },
        openModalRegisterAddress() {
            this.isDisplayRegisterAddress = "display: flex;"
        },
    }
}
</script>

<style>

.style-btn-red {
    padding: 5px;
    width: 3vw;
    border-radius: 4px;
    background-color: rgb(255, 0, 0);
    color: rgb(255, 255, 255);
    border: 2px solid #b44301; 
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}

.style-btn-red:hover {
    background-color: #ffffff; /* Green */
    color: rgb(255, 2, 2);
}

.style-btn-green {
    padding: 5px;
    width: 3vw;
    border-radius: 4px;
    background-color: rgb(21, 138, 11);
    color: rgb(255, 255, 255);
    border: 2px solid rgb(11, 122, 1); /* Green */
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}

.style-btn-green:hover {
    background-color: #ffffff; /* Green */
    color: rgb(21, 138, 11);
}

.style-btn-dark {
    padding: 5px;
    width: 3vw;
    border-radius: 4px;
    background-color: rgb(49, 49, 49);
    color: rgb(255, 255, 255);
    border: 2px solid rgb(0, 0, 0); /* Green */
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}

.style-btn-dark:hover {
    background-color: #ffffff; /* Green */
    color: rgb(2, 2, 2);
}

.style-btn-yellow {
    padding: 5px;
    width: 3vw;
    border-radius: 4px;
    background-color: #E0A800;
    color: rgb(255, 255, 255);
    border: 2px solid #aa8000; /* Green */
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}

.style-btn-yellow:hover {
    background-color: #ffffff; /* Green */
    color: #E0A800;
}

</style>