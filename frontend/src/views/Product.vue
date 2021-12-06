<template>
    <div>
        <Nav-bar/>
        <Side-bar/>
        <div class="container justify-content-center mt-2 mb-5">
            <div class="row" id="head" style="background-color: white;border:2px solid rgb(232, 232, 232)">
                <div class="ml-5">
                    <h3 class="row" style="color: #FCB13A;">{{title}}</h3>
                    <h5 class="row" style="color: #FCB13A;">{{subtitle}}</h5>
                </div>
            </div>
            <div class="row d-flex justify-content-between mt-3">
                <div class="col text-center" id="imagemMedia" style="background-color: white;border:2px solid rgb(232, 232, 232)">
                    <img :src="link" class="row w-50" style="margin: auto;">
                    <b-form-rating class="row" id="media" inline color="#FCB13A" value="4" style="margin-left: 10vw;margin-right: 10vw;border: none;"/>
                </div>
                <div class="col ml-5">
                    <div class="row justify-content-start p-3 h-75" id="pricesContent" style="background-color: white;border:2px solid rgb(232, 232, 232)">
                        <div class="col h-75">
                            <div class="row ml-5 mt-3">
                                <h4 class="" style="font-size: larger;color: gray;text-decoration: line-through;font-weight: bolder;">R$ {{desconto}}</h4>
                            </div>
                            <div class="row ml-4">
                                <div class="p-3" style="background-color: #9652C6;color: white;border-radius: 10px 0px 0px 10px;">
                                    <h5 style="padding: auto;margin: auto;">R$ {{preco}}</h5>
                                </div>
                            </div>
                            <div class="ml-3 mt-3 mb-3 mt-5">
                                <i class="far fa-credit-card fa-2x" style="color: rebeccapurple;"/><span style="color: gray;font-size: large;">Credicard: R$ {{preco}}</span>
                            </div>
                            <button class="col rounded p-1" style="background-color: #FCB13A;border: none;color: white;">ADD TO CART</button>
                        </div>
                    </div>
                    <div class="row p-2 mt-2" id="delivery" style="background-color: white;border:2px solid rgb(232, 232, 232)">
                        <span class="d-flex justify-content-center p-2" style="color: #FCB13A;align-items: center;">Calcule Delivery
                            <input type="text" class="w-50 ml-1 p-2" style="background: #ECECEC;border-radius: 10px 0px 0px 10px;border: none;">
                            <button class="p-2" style="background: #FCB13A;border-radius: 0px 10px 10px 0px; border: none;color: white;">Calculate</button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="row mt-3 p-4" id="description" style="background-color: white;border:2px solid rgb(232, 232, 232)">
                <span><h5>Description</h5></span>
                <p>{{description}}</p>
            </div>
            <!-- <div class="row mt-3 p-3" id="characteristics" style="background-color: white;border:2px solid rgb(232, 232, 232)">
                <div class="col p-3">
                    <span><h5>Characteristics</h5></span>
                    <div id="characteristicsContent">
                        <div class="d-flex justify-content-between" >
                            <div class="d-flex w-50 border border-end p-2">
                                Finishing
                            </div>
                            <div class="d-flex w-50 border border-end p-2">
                                Brochure
                            </div>
                        </div>
                        <div class="d-flex justify-content-between p-2" >
                            <div class="d-flex w-50 border border-end p-2">
                                Finishing
                            </div>
                            <div class="d-flex w-50 border border-end p-2">
                                Brochure
                            </div>
                        </div>
                    </div>
                </div>
            </div> -->
            <div class="row mt-3 border border-5 p-4" id="evaluate" style="background-color: white;border:2px solid rgb(232, 232, 232)">
                <div class="col p-2">
                    <h4 class="row">Evaluate</h4>
                    <div class="col">
                        <div class="row m-3 mt-4">
                            <i class="fas fa-user-circle fa-5x" style="color: #FCB13A;"></i>
                            
                            <div class="col justify-content-start pl-5">
                                <b-form-rating class="row" id="rating-inline" inline color="#FCB13A" value="4" style="border: none;padding: 0px;"/>
                                <span class="row">Cool</span>
                            </div>
                        </div>
                        <div class="row m-3 mt-4">
                            <i class="fas fa-user-circle fa-5x" style="color: #FCB13A;"></i>
                            
                            <div class="col justify-content-start pl-5">
                                <b-form-rating class="row mt-" id="rating-inline" inline color="#FCB13A" value="4" style="border: none;padding: 0px;"/>
                                <span class="row">Great</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <Footer/>
    </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import SideBar from '@/components/SideBar.vue'

export default {
    name: "Product",
    components: {NavBar, Footer, SideBar},
    data() {
        return {
            title:"As cronicas de Nárnia",
            subtitle:"Author: Lewis, Clives, Staples",
            link:"https://www.thebookshop.pl/images/thebookshop/8000-9000/The-Complete-Chronicles-of-Narnia-by-C-S-Lewis_[8525]_568.jpg",
            preco:"37,90",
            desconto: "99,90",
            characteristics:[
                {type:"finish", content:"Brochure"},
                {type:"finish", content:"Brochure"},
            ],
            description:"Experience all seven tales of C. S. Lewis's classic fantasy series, The Chronicles of Narnia, in one impressive paperback volume! Epic battles between good and evil, fantastic creatures, betrayals, heroic deeds, and friendships won and lost all come together in this unforgettable world, which has been enchanting readers of all ages for over sixty years. This edition presents the seven books—The Magician's Nephew; The Lion, the Witch and the Wardrobe; The Horse and His Boy;Prince Caspian; The Voyage of the Dawn Treader; The Silver Chair; and The Last Battle—unabridged and arranged in C. S. Lewis's preferred order. Each chapter is graced with an illustration by the original artist, Pauline Baynes. ",
        }
    },
}
</script>

<style scoped>
    #head, #imagemMedia,#pricesContent,#delivery,#description,#characteristics,#evaluate{
        border-radius: 25px;
    }
</style>