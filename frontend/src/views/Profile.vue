<template>
    <div class="profile">
        <Nav-bar/>
        <section>
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
        </section>
        <Footer/>
    </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'

export default {
    name: "Profile",
    components: {NavBar, Footer}
}
</script>

<style scoped>
section {
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
}

</style>