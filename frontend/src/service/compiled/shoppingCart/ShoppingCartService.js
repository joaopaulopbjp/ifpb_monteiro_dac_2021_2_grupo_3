"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ShoppingCartService = void 0;
class ShoppingCartService {
    renderItens() {
        let itemDiv = document.getElementById("itemsDiv");
        let containerValue = document.getElementById("containerValue");
        fetch('http://localhost:8080/api/shopping-cart/find-shoppingcart', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${window.localStorage.getItem("token")}`
            }
        }).then(async function (response) {
            let json = await response.json();
            let html = "";
            let arrayIdItemElement = [];
            let arrayIdProduct = [];
            let subTotal = 0;
            let Transport = 50;
            if (json["itemList"].length === 0) {
                itemDiv.innerHTML = `
                    <div class = "d-flex flex-column text-center">
                    <i class="fas fa-cart-plus mb-3 fa-5x"></i>
                    <h3>Shopping Cart Empty</h3>
                    </div>
                `;
                return;
            }
            json["itemList"].forEach(element => {
                html += `
              <div id="bookShoppingcart" class="container rounded mt-2 p-3" style="background-color: white;border-radius: 1px solid black;" name="${element["id"]}">
                <div class="d-flex justify-content-end p-1">
                <button id="trashButtonShoppingcart" value="${element["id"]}" variant="outline-dark" class="border-0 far fa-trash-alt" style="background-color: rgba(0, 0, 0, 0);border: none;font-size: 3vh"/>
            </div>
            <div class="d-flex justify-content-between">
                <div class="d-flex justify-content-between">
                    <button class="text-decoration-none text-dark " style="background-color: rgba(0, 0, 0, 0);border:none;">
                        <img id="bookShoppingcartImage" value=${element["product"]["id"]} class="" width=200vw height=180vh style="max-width: 120px" src="${element["product"]["imageList"][0]["base64"]}">
                    </button>
                    <div class="d-grid ml-4">
                        <h4>${element["product"]["title"]}</h4>
                        <h6>Author: ${element["product"]["authorList"][0]["name"]}</h6>
                        <div class="row justify-content-center mt-5  rounded" style="background-color: #9652C6;color: white;align-items: center; width: 15vw;">
                            <h5>R$: ${element["product"]["price"]}</h5>
                        </div>
                    </div>
                    </div>
                    <div class=" mt-4" >
                        <button id="leftButton${element["id"]}" name="${element["id"]}" class="text-center buttonMinus rounded-left">-</button>
                        <input id="amount${element["id"]}" class="text-center rounded inputMeio" type="number" min="1" value="${element["amount"]}" >
                        <button id="rightButton${element["id"]}" name="${element["id"]}" class="text-center buttonPlus rounded-right">+</button>
                    </div>
                    </div> 
                    <hr>
            </div>`;
                subTotal += element["product"]["price"] * element["amount"];
                arrayIdItemElement.push(`${element["id"]}`);
                arrayIdProduct.push(`${element["product"]["id"]}`);
            });
            itemDiv.innerHTML = html;
            for (let index = 0; index < arrayIdItemElement.length; index++) {
                const idItem = arrayIdItemElement[index];
                document.getElementById(`leftButton${idItem}`)
                    .addEventListener("click", () => {
                    let input = document.getElementById(`amount${idItem}`);
                    if ((Number(input.value) - 1) > 0) {
                        input.value = (Number(input.value) - 1).toString();
                    }
                    fetch('http://localhost:8080/api/shopping-cart/add', {
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json',
                            "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                        },
                        body: JSON.stringify({
                            itemList: [{
                                    amount: input.value,
                                    idProduct: arrayIdProduct[index]
                                }]
                        })
                    }).then(response => {
                        if (response.status === 200) {
                            let containerValue = document.getElementById("containerValue");
                            fetch('http://localhost:8080/api/shopping-cart/find-shoppingcart', {
                                method: 'GET',
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json',
                                    "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                                }
                            }).then(async function (response) {
                                let json = await response.json();
                                let subTotal = 0;
                                let Transport = 50;
                                json["itemList"].forEach(element => {
                                    subTotal += element["product"]["price"] * element["amount"];
                                    containerValue.innerHTML = `
                                <div class="row rounded-top w-100 mb-0 pl-4 pt-3" style="background-color: white;">
                                    <div class="">
                                        <h4>Order:</h4>
                                        <h5>Subtotal: R$: ${subTotal}</h5>
                                        <h5>Transport: R$: ${Transport}</h5>
                                        <hr class="ml-5 w-50">
                                        <h5>Total: R$: ${subTotal + Transport}</h5>
                                    </div>
                                </div>
                                <button id="buyButtonShoppingcart" class="row d-flex justify-content-center rounded-bottom w-100" style="background-color: #FCB13A;border: none;color: white;">buy</button>
                            `;
                                });
                            });
                        }
                    });
                });
                document.getElementById(`rightButton${idItem}`)
                    .addEventListener("click", () => {
                    let input = document.getElementById(`amount${idItem}`);
                    input.value = (Number(input.value) + 1).toString();
                    fetch('http://localhost:8080/api/shopping-cart/add', {
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json',
                            "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                        },
                        body: JSON.stringify({
                            itemList: [{
                                    amount: input.value,
                                    idProduct: arrayIdProduct[index]
                                }]
                        })
                    }).then(() => {
                        let containerValue = document.getElementById("containerValue");
                        fetch('http://localhost:8080/api/shopping-cart/find-shoppingcart', {
                            method: 'GET',
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json',
                                "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                            }
                        }).then(async function (response) {
                            let json = await response.json();
                            let subTotal = 0;
                            let Transport = 50;
                            json["itemList"].forEach(element => {
                                subTotal += element["product"]["price"] * element["amount"];
                                containerValue.innerHTML = `
                          <div class="row rounded-top w-100 mb-0 pl-4 pt-3" style="background-color: white;">
                              <div class="">
                                  <h4>Order:</h4>
                                  <h5>Subtotal: R$: ${subTotal}</h5>
                                  <h5>Transport: R$: ${Transport}</h5>
                                  <hr class="ml-5 w-50">
                                  <h5>Total: R$: ${subTotal + Transport}</h5>
                              </div>
                          </div>
                          <button id="buyButtonShoppingcart" class="row d-flex justify-content-center rounded-bottom w-100" style="background-color: #FCB13A;border: none;color: white;">buy</button>
                      `;
                            });
                        });
                    });
                });
            }
            containerValue.innerHTML = `
            <div class="row rounded-top w-100 mb-0 pl-4 pt-3" style="background-color: white;">
                <div class="">
                    <h4>Order:</h4>
                    <h5>Subtotal: R$: ${subTotal}</h5>
                    <h5>Transport: R$: ${Transport}</h5>
                    <hr class="ml-5 w-50">
                    <h5>Total: R$: ${subTotal + Transport}</h5>
                </div>
            </div>
            <button id="buyButtonShoppingcart" class="row d-flex justify-content-center rounded-bottom w-100" style="background-color: #FCB13A;border: none;color: white;">buy</button>
          `;
            itemDiv.querySelectorAll('[id=bookShoppingcartImage]').forEach(element => {
                element;
                element.addEventListener("click", () => {
                    window.localStorage.setItem("productId", element.attributes.getNamedItem("value").value);
                    window.location.replace("http://localhost:8081/#/product");
                });
            });
            itemDiv.querySelectorAll('[id=trashButtonShoppingcart]').forEach(element => {
                element.addEventListener("click", () => {
                    fetch('http://localhost:8080/api/shopping-cart/remove', {
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json',
                            "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                        },
                        body: JSON.stringify({
                            itemList: [{
                                    id: element.value
                                }]
                        })
                    }).then(() => {
                        window.location.reload();
                    });
                });
            });
            document.getElementById("buyButtonShoppingcart")
                .addEventListener("click", () => {
                window.location.replace("#/Payment");
            });
        });
    }
}
exports.ShoppingCartService = ShoppingCartService;
