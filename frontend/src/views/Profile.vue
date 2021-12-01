<template>
    <div>
        <Nav-bar/>
        <Side-bar/>
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
                            <b-button to="RegisterProduct" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-plus-circle"></i> Register Author
                            </b-button>
                            <b-button pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-trash-alt"></i> Delete Author
                            </b-button>
                            <b-button pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-edit"></i> Update Author
                            </b-button>
                        </b-button-group>
                    </div>
                    <div class="ml-3">
                        <h6>Target category</h6>
                        <b-button-group vertical>
                            <b-button to="RegisterProduct" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-plus-circle"></i> Register Category
                            </b-button>
                            <b-button pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-trash-alt"></i> Delete Category
                            </b-button>
                            <b-button pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-edit"></i> Update Category
                            </b-button>
                        </b-button-group>
                    </div>
                    <div class="ml-3">
                        <h6>Target company</h6>
                        <b-button-group vertical>
                            <b-button to="RegisterProduct" pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-plus-circle"></i> Register Company
                            </b-button>
                            <b-button pill variant="warning" class="my-2 mb-3" type="submit">
                                <i class="fas fa-trash-alt"></i> Delete Company
                            </b-button>
                            <b-button pill variant="warning" class="my-2 mb-3" type="submit">
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

import { ProfileApi } from "../service/compiled/profile/ProfileApi.js";
let profileApi = new ProfileApi();

export default {
    name: "Profile",
    components: { NavBar, Footer, SideBar},
    mounted() {
        this.setInfoOnVue()
        profileApi.editButtonEvent();
        profileApi.saveButtonEvent();
    },
    beforeMount() {
        this.loggedVerify();
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
        }
    }
}
</script>

<style scoped>

</style>