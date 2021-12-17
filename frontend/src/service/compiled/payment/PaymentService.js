"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.PaymentService = void 0;
const AddressService_1 = require("../address/AddressService");
class PaymentService {
    addAddressOnVue() {
        let addressService = new AddressService_1.AddressService();
        addressService.getAddressInfo().then(async (response) => {
            if (response !== null) {
                let json = await response.json();
                let addressDiv = document.getElementById("address");
                let html = addressDiv.innerHTML;
                html += `
                <div class="d-flex justify-content-between p-4">
                    <div class="">
                        <h5 class="">${window.localStorage.getItem("username")}</h5>
                        <h5 class="mt-3">${json[0]["street"]}, ${json[0]["number"]}</h5>
                        <h5>${json[0]["city"]}, ${json[0]["district"]}, ${json[0]["zipCode"]}</h5>
                    </div>
                    <div class="">
                        <b-button style="background-color: transparent;color: orange;border: none;">select another address</b-button>
                    </div>
                </div>
                `;
                addressDiv.innerHTML = html;
            }
        });
    }
    addOrderlistOnVue() {
        fetch('http://localhost:8080/api/shopping-cart/find-shoppingcart', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${window.localStorage.getItem("token")}`
            }
        }).then(async function (response) {
            let orderDiv = document.getElementById("orderList");
            let orderSummary = document.getElementById("orderSummary");
            let json = await response.json();
            let html = "";
            let transport = 50;
            json["itemList"].forEach(element => {
                html += `
                <div id="itemToBuy" name="${element["id"]}" class="d-flex justify-content-start m-3">
                    <button style="border: none" id="buttonImageOrder" value="${element["product"]["id"]}">
                        <img class="" style="height: 20vh; width:7vw;" src="${element["product"]["imageList"][0]["base64"]}" alt="">
                    </button>
                    <div class="d-grid ml-4">
                        <h4>${element["product"]["title"]}</h4>
                        <h6>Author: ${element["product"]["authorList"][0]["name"]}</h6>
                        <h6>Amount order: ${element["amount"]}</h6>
                        <div class="row justify-content-center mt-5 w-10 rounded" style="background-color: #9652C6;color: white;align-items: center;">
                            <h5>R$: ${element["totalPrice"]}</h5>
                        </div>
                    </div>
                </div>
                `;
            });
            orderDiv.innerHTML = html;
            orderDiv.querySelectorAll('[id=buttonImageOrder]').forEach(element => {
                element;
                element.addEventListener("click", () => {
                    window.localStorage.setItem("productId", element.attributes.getNamedItem("value").value);
                    window.location.replace("http://localhost:8081/#/product");
                });
            });
            orderSummary.innerHTML = `
            <div class="">
                <h3 class="p-2">Order Summary</h3>
                <h5>value: R$: ${json["totalPrice"]}</h5>
                <h5>shipping: R$: ${transport}</h5>
                <hr class="ml-4 w-75">
                <h5>Total: R$: ${json["totalPrice"] + transport}</h5>
            </div>
            `;
        });
    }
    addCheckoutEvent() {
        document.getElementById("checkout")
            .addEventListener("click", () => {
            let orderDiv = document.getElementById("orderList");
            let idList = [];
            orderDiv.querySelectorAll('[id=itemToBuy]').forEach(element => {
                idList.push(element.attributes.getNamedItem("name").value);
            });
            let namePayment = document.getElementById("namePayment");
            let cpfPayment = document.getElementById("cpfPayment");
            let ccnPayment = document.getElementById("ccnPayment");
            let dataPayment = document.getElementById("dataPayment");
            let cvcPayment = document.getElementById("cvcPayment");
            let flag = true;
            if (namePayment.value === "") {
                namePayment.classList.add("is-invalid");
                flag = false;
            }
            if (cpfPayment.value === "") {
                cpfPayment.classList.add("is-invalid");
                flag = false;
            }
            if (ccnPayment.value === "") {
                ccnPayment.classList.add("is-invalid");
                flag = false;
            }
            if (dataPayment.value === "") {
                dataPayment.classList.add("is-invalid");
                flag = false;
            }
            if (cvcPayment.value === "") {
                cvcPayment.classList.add("is-invalid");
                flag = false;
            }
            if (flag) {
                fetch('http://localhost:8080/api/order/save', {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                    },
                    body: JSON.stringify({
                        idItemList: idList
                    })
                }).then(apiResponse => {
                    if (apiResponse.status === 201) {
                        window.location.replace("/");
                    }
                });
            }
        });
    }
}
exports.PaymentService = PaymentService;
