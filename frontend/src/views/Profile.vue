<template>
    <div>
        <Nav-bar/>
        <Side-bar/>
        <div class="container mt-2 mb-5">
            <b-card>
                <div class="d-flex justify-content-between mb-4">
                    <h4><i class="fas fa-user-circle"></i> Overview</h4>
                    <b-button variant="warning" class="my-2 my-sm-0" type="submit">
                    <i class="fas fa-pen"></i>
                    </b-button>
                </div>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-input type="text" placeholder="Fulano beltrano" style="width: 400px" disabled></b-form-input>
                    <b-form-input class="ml-4" type="text" placeholder="fulanobeltrano@gmail.com" style="width: 500px" disabled></b-form-input>
                </div>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-input type="text" placeholder="Old password" style="width: 250px" disabled></b-form-input>
                    <b-form-input class="ml-4" type="text" placeholder="New password" style="width: 250px" disabled></b-form-input>
                    <b-form-input class="ml-4" type="text" placeholder="Confirm password" style="width: 250px" disabled></b-form-input>
                </div>
                <b-button variant="outline-success" class="my-2 my-sm-0" type="submit">
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
                    <b-form-input type="text" placeholder="Fulano beltrano" style="width: 400px" disabled></b-form-input>
                </div>
            </b-card>
        </div>
        <div class="container mt-2 mb-2">
            <b-card>
                <div class="d-flex justify-content-between mb-4">
                    <h4><i class="fas fa-map-marker-alt"></i> Address</h4>
                    <b-button variant="warning" class="my-2 my-sm-0" type="submit">
                    <i class="fas fa-pen"></i>
                    </b-button>
                </div>
            </b-card>
        </div>
        <Footer/>
       <!--  <section>
            <div class="headSection">
                <h1>Overview</h1>
                <button type="submit">Edit</button>
            </div>
            <div>
                <input type="text" name="" id="" placeholder="Brito Silva" disabled>
                <input type="text" name="" id="" placeholder="britosilva@gmail.com" disabled >
            </div>
            <div>
                <input type="text" name="" id="" placeholder="old password" disabled>
                <input type="text" name="" id="" placeholder="new password" disabled>
                <input type="text" name="" id="" placeholder="confirm password" disabled>
            </div>
            <button class="btnSave" type="submit">Save</button>
        </section>
       
        <section id="boxRequests">
            <div class="headSection">
                <h1>Requests</h1>
            </div>
            <section id="boxProduct">
                <div>
                    <p>A garota do lago</p>
                    <img id="imgProduct" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
                    <p>R$ 16,90</p>
                </div>
            </section>
            <section id="boxProduct">
                <div>
                    <p>A garota do lago</p>
                    <img id="imgProduct" src="https://lojasaraiva.vteximg.com.br/arquivos/ids/12109069/1006574337.jpg?v=637142248039070000" alt="">
                    <p>R$ 16,90</p>
                </div>
            </section>
        </section>

        <section >
            <div class="headSection">
                <h1>Address</h1>
                <button class="btnAddAddress" type="submit">Add new Address</button>
            </div>
            <div class="headSection">
                <section id="boxProduct">
                    <div>
                        <p>Av principal</p>
                        <p>Alto de cima</p>
                        <p>monteiro, paraiba</p>
                        <p>Brasil</p>
                    </div>
                </section>
                <section id="boxProduct">
                    <div>
                        <p>Av principal</p>
                        <p>Alto de cima</p>
                        <p>monteiro, paraiba</p>
                        <p>Brasil</p>
                    </div>
                </section>
                <section id="boxProduct">
                    <div>
                        <p>Av principal</p>
                        <p>Alto de cima</p>
                        <p>monteiro, paraiba</p>
                        <p>Brasil</p>
                    </div>
                </section>
            </div>
        </section> -->
    </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import SideBar from '@/components/SideBar.vue'

export default {
    name: "Profile",
    components: {NavBar, Footer, SideBar}
}
</script>

<style scoped>
/* section {
    display: flexbox;
    margin-top: 30px;
    margin-bottom: 30px;
    padding: 10px 30px;
    justify-content: space-between;
    width: 80%;
    margin-left: auto;
    margin-right: auto;
    background: white;
    border-radius: 5px;
    border: 1px solid rgb(170, 170, 170);

}

#boxProduct {
    display: flex;
    width: 20%;
    margin-inline-start: 0;
    justify-content: center;
}

#boxRequests {
    display: flex;
}

#imgProduct {
    max-width: 120px;
}

.headSection {
    display: flex;
    justify-content: space-between;
}

.btnAddAddress, .btnSave {
    padding: 8px 8px;
    background: #FCB13A;
    border-radius: 5px;
    border: 1px rgb(170, 170, 170);
}

input {
    margin: 20px;
    padding: 8px 8px;
    border-radius: 5px;
    border: 1px solid rgb(170, 170, 170);
    background: #B17FD5;
}

p {
    margin: 10px;
} */

</style>