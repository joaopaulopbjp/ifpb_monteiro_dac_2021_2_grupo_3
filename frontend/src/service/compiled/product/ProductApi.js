"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProductApi = void 0;
const Base64_1 = require("../utils/Base64");
class ProductApi {
    generateStarButton(starNumber) {
        let html = `<fieldset class="rating mb-4">`;
        for (let index = 1; index <= 5; index++) {
            if (index <= starNumber) {
                html += `<span class="fa fa-star checked"></span>`;
            }
            else {
                html += `<span class="fa fa-star"></span>`;
            }
        }
        html += `</fieldset>`;
        return html;
    }
    generateStarAverage(array) {
        let number = 0;
        array.forEach(element => {
            number += element["starNumber"];
        });
        return (number / array.length);
    }
    generateAuthorList(array) {
        let text = "";
        for (let index = 0; index < array.length - 1; index++) {
            text += `${array[index]["name"]}, `;
        }
        text += array[array.length - 1]["name"];
        return text;
    }
    addProductInfoOnVue() {
        fetch('http://localhost:8080/api/book/find/find-by-id', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${window.localStorage.getItem("token")}`
            },
            body: JSON.stringify({
                id: window.localStorage.getItem("productId")
            })
        }).then(async (apiResponse) => {
            let json = await apiResponse.json();
            document.getElementById("titleInfoProduct").innerHTML = `
            <h3 class="row" style="color: #FCB13A;">${json["title"]}</h3>
            <h5 class="row" style="color: #FCB13A;">${this.generateAuthorList(json["authorList"])}</h5>
            `;
            document.getElementById("imagemMediaProduct").innerHTML = `
            <img src="${json["imageList"][0]["base64"]}" height="300vh" width="250vw" class="row mt-4" style="margin-left: auto;margin-right: auto;">
            ${this.generateStarButton(this.generateStarAverage(json["evaluateList"]))}
            `;
            document.getElementById("priceProduct").innerHTML = `
            <h5 style="padding: auto;margin: auto;">R$ ${json["price"]}</h5>
            `;
            document.getElementById("credicardProduct").innerHTML = `
            <i class="far fa-credit-card fa-2x" style="color: rebeccapurple;"/><span style="color: gray;font-size: large;">Credicard: R$ ${json["price"]}</span>
            `;
            document.getElementById("descriptionProduct").innerHTML = `
            <span><h5>Description</h5></span>
            <p>${json["description"]}</p>
            `;
            json["evaluateList"].forEach(element => {
                fetch('http://localhost:8080/api/evaluate/find-image-by-id', {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                    },
                    body: JSON.stringify({
                        id: element["id"]
                    })
                }).then(async (apiResponse) => {
                    if (apiResponse.ok) {
                        let evaluate = document.getElementById("evaluateProduct").innerHTML;
                        let imageJson = await apiResponse.json();
                        evaluate += `
                        <div class="row m-3 mt-4">
                            <img src="${imageJson["response"]}" width="100vw" height="100vw" style="border-radius: 50%"/>
                            
                            <div class="col justify-content-start pl-5">
                                ${this.generateStarButton(element["starNumber"])}
                                <span class="row">${element["comment"]}</span>
                            </div>
                        </div>
                        `;
                        document.getElementById("evaluateProduct").innerHTML = evaluate;
                    }
                });
            });
        });
    }
    saveEvent() {
        let savebutton = document.getElementById("saveButton");
        let titleInput = document.getElementById("titleInput");
        let yearLaunchInput = document.getElementById("yearLaunchInput");
        let pageInput = document.getElementById("pageInput");
        let priceInput = document.getElementById("priceInput");
        let inventoryInput = document.getElementById("inventoryInput");
        let companyInput = document.getElementById("companyInput");
        let imageInput = document.getElementById("imageInput");
        let descriptionInput = document.getElementById("descriptionInput");
        savebutton.addEventListener("click", async () => {
            let base64 = new Base64_1.Base64();
            let idAuthorArray = this.getIdListFromCheckboxId("authorCheckBox");
            let idCategoryArray = this.getIdListFromCheckboxId("categoriesCheckBox");
            if (imageInput.files[0] !== undefined) {
                base64.urlToBase64(imageInput.files[0]).then(async function (image) {
                    fetch('http://localhost:8080/api/book', {
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json',
                            "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                        },
                        body: JSON.stringify({
                            title: titleInput.value,
                            description: descriptionInput.value,
                            yearLaunch: yearLaunchInput.value,
                            pages: pageInput.value,
                            price: priceInput.value,
                            inventory: {
                                amount: inventoryInput.value
                            },
                            imageList: [{
                                    base64: image
                                }],
                            idCategoryList: idCategoryArray,
                            idCompany: companyInput.value,
                            idAuthorList: idAuthorArray
                        })
                    }).then(apiResponse => {
                        if (apiResponse.status === 201) {
                            document.getElementById("cancelRegisterProduct").click();
                        }
                    });
                });
            }
        });
    }
    getIdListFromCheckboxId(id) {
        let idArray = [];
        let elementArray;
        document.querySelectorAll(`#${id}`).forEach(element => {
            elementArray = element;
            if (elementArray.checked) {
                idArray.push(elementArray.value);
            }
        });
        return idArray;
    }
}
exports.ProductApi = ProductApi;
