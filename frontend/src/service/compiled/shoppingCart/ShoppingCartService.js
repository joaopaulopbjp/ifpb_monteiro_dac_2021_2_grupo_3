"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ShoppingCartService = void 0;
class ShoppingCartService {
    renderItens() {
        let itemDiv = document.getElementById("itemsDiv");
        fetch('http://localhost:8080/api/shopping-cart/find-shoppingcart', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${window.localStorage.getItem("token")}`
            }
        }).then(async function (response) {
            let json = await response.json();
            let html = itemDiv.innerHTML;
            json["itemList"].forEach(element => {
                html += `
                <b-card class="container rounded mt-2" value="${element["id"]}">
                    <div class="d-flex justify-content-between">
                        <b-form-checkbox></b-form-checkbox>
                        <b-button variant="outline-dark border-0"><i class="far fa-trash-alt w-100 h-100"></i></b-button>
                    </div>
                    <div class="d-flex justify-content-between">
                        <div class="d-flex justify-content-between">
                            <router-link class="text-decoration-none text-dark " to="">
                                <img class="" style="max-width: 120px" src="${element["product"]["imageList"]["base64"]}" alt="">
                            </router-link>
                            <div class="d-grid ml-4">
                                <h4>${element["product"]["title"]}</h4>
                                <h6>Author: Charlie Donlea</h6>
                                <div class="row justify-content-center mt-5 w-10 rounded" style="background-color: #9652C6;color: white;align-items: center;">
                                    <h5>R$: 17,90</h5>
                                </div>
                            </div>
                        </div>
                        <b-form-spinbutton class="mt-4" id="sb-inline" v-model="quantidade" inline style="background-color: #FCB13A;"></b-form-spinbutton>
                    </div>
                </b-card>`;
            });
            itemDiv.innerHTML = html;
        });
    }
}
exports.ShoppingCartService = ShoppingCartService;
