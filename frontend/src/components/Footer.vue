<template>
    <div>
        <Github :style="isDisplayGithub" @click="closeModalGithub"/>
        <Linkedin :style="isDisplayLinkedin" @click="closeModalLinkedin"/>
        <footer class="page-footer" style="background-color: #955DBC">
        <div style="background-color: #FCB13A">
            <div class="container">
                <div class="row py-4 d-flex align-items-center">
                    <div class="col-md-12 text-center">
                        <p class="d-inline mr-4">Get connected with us on social networks</p>
                        <i @click="openModalLinkedin()" class="fab fa-linkedin-in text-dark mr-4 fa-2x"></i>
                        <i @click="openModalGithub()" class="fab fa-github-alt text-dark mr-4 fa-2x"></i>
                    </div>
                </div>
            </div>
        </div>

        <div class="container text-center text-md-left mt-5">
            <div class="row">

                <div class="col-md-3 mx-auto mb-4">
                    <h6 class="text-uppercase font-weight-bold">the developers</h6>
                    <hr class="bg-warning mb-4 mt-0 d-inline-block mx-auto" style="width: 160px; height: 2px">
                    <p class="p-2">João Arthur<br>Lucas Nascimento<br>Thauan Amorim</p>
                </div>

                <div class="col-md-3 mx-auto mb-4">
                    <h6 class="text-uppercase font-weight-bold">project for purposes</h6>
                    <hr class="bg-warning mb-4 mt-0 d-inline-block mx-auto" style="width: 225px; height: 2px">
                    <p class="p-2">This project was developed for academic purposes, and targeted the discipline of DAC</p>
                </div>
            </div>
        </div>

    </footer>
    </div>
</template>

<script>
import Github from "../modal/Github.vue";
import Linkedin from "../modal/Linkedin.vue";

export default {
    data() {
        return {
            isDisplayLinkedin:"display: none;",
            isDisplayGithub:"display: none;",
        }
    },
    methods: {
        openModalLinkedin() {
            this.isDisplayLinkedin = "display: flex;"
        },
        closeModalLinkedin(){
            this.isDisplayLinkedin = "display: none;"
        },
        openModalGithub() {
            this.isDisplayGithub = "display: flex;"
        },
        closeModalGithub(){
            this.isDisplayGithub = "display: none;"
        },
    },
    components: {Github, Linkedin }
}
</script>

<style scoped>

.footer {
    height: 160px;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    border-top: 50px solid #FCB13A;
    background: #955ABE;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
}

.line {
    height: 90px;
    width: 1px;
    background: black;
}

p {
    padding: 0px 100px;
    font-size: 18px;
}

.labelLinks {
    margin-bottom: 75px;
}

</style>