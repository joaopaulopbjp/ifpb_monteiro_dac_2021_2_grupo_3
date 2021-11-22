<template>
    <div class="registerProduct">
        <Nav-bar/>
        <Side-bar/>
        <div class="container mt-2 mb-5">
            <b-card>
                <div class="d-flex justify-content-between ml-5"><h1>Register a Book</h1></div>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-input type="text" placeholder="Title" style="width: 65%" class="ml-5"></b-form-input>
                    <b-form-input type="number" placeholder="YearLaunch" style="width: 40%" class="ml-5"></b-form-input>
                    <b-form-input type="number" placeholder="Page" style="width: 40%" class="ml-5 mr-5"></b-form-input>
                </div>
                <div class="d-flex justify-content-start mb-4">
                    <b-form-input type="number" placeholder="Price" style="width: 30%" class="ml-5"></b-form-input>
                    <b-form-input type="number" placeholder="Inventory" style="width: 40%" class="ml-5"></b-form-input>

                    <b-form-select v-model="campanySelected" :options="companys" style="width: 45%" class="ml-5"></b-form-select>

                    <b-form-file v-model="Array" style="width: 45%" class="ml-5 mr-5"></b-form-file>
                    
                </div>
                <div class="d-flex justify-content-between mb-4">
                    <b-card class="ml-5 mr-5 w-45 p-3">
                        <h4>Authors</h4>
                        
                        <b-form-checkbox-group
                        v-model="selected"
                        :options="authors"
                        :aria-describedby="ariaDescribedby"
                        name="flavour-1a"
                        stacked
                        ></b-form-checkbox-group>
                    </b-card>

                    <b-form-textarea v-model="text" placeholder="Description" class="md-textarea p-3" style="width: 45%; "></b-form-textarea>

                    <b-card class="ml-5 mr-5 w-45 p-3">
                        <h4>Categories</h4>
                        <b-form-checkbox-group
                        v-model="selected"
                        :options="categories"
                        :aria-describedby="ariaDescribedby"
                        stacked></b-form-checkbox-group>
                    </b-card>
                </div>
                <div class="d-flex justify-content-center">
                    <b-button to="Profile" variant="danger" class="mt-4 mr-2">Cancel</b-button>
                    <b-button variant="warning" class="mt-4">Register Book</b-button>
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

export default {
    name: "RegisterProduct",
    components: {NavBar, Footer,SideBar},
    
    data() {
      return {
        // parte que coloca os autores no b-card do authors
        authors: [
          { text: 'Lewis', value: 'Lewis' },
          { text: 'Clives', value: 'Clives' },
        ],
        categories:[
            {text: "Adventure", value: "Adventure"},
            {text: "Horror", value: "Horror"}
        ],
        companys: [
            {value: null, text: "Please select an company"},
            {value: "saraiva", text: "saraiva"}
        ],
        campanySelected: null,
      }
    }
}
</script>

<style scoped>
    
</style>