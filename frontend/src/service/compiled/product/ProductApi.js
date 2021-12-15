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
            await json["evaluateList"].forEach(element => {
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
                        <div class="row m-3 mt-4 p-3" style="border: 1px solid #D3D3D3; border-radius: 1vw">
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
            document.getElementById("addToCartProduct")
                .addEventListener("click", () => {
                fetch('http://localhost:8080/api/shopping-cart/add', {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                    },
                    body: JSON.stringify({
                        itemList: [{
                                amount: 1,
                                idProduct: window.localStorage.getItem("productId")
                            }]
                    })
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
        let categoryInput = document.getElementById("categoryBox");
        let authorInput = document.getElementById("authorBox");
        savebutton.addEventListener("click", async () => {
            let base64 = new Base64_1.Base64();
            let idAuthorArray = this.getIdListFromCheckboxId("authorCheckBox");
            let idCategoryArray = this.getIdListFromCheckboxId("categoriesCheckBox");
            if (titleInput.value === "") {
                titleInput.classList.add("is-invalid");
            }
            else {
                titleInput.classList.remove("is-invalid");
            }
            if (yearLaunchInput.value === "") {
                yearLaunchInput.classList.add("is-invalid");
            }
            else {
                yearLaunchInput.classList.remove("is-invalid");
            }
            if (pageInput.value === "") {
                pageInput.classList.add("is-invalid");
            }
            else {
                pageInput.classList.remove("is-invalid");
            }
            if (priceInput.value === "") {
                priceInput.classList.add("is-invalid");
            }
            else {
                priceInput.classList.remove("is-invalid");
            }
            if (inventoryInput.value === "") {
                inventoryInput.classList.add("is-invalid");
            }
            else {
                inventoryInput.classList.remove("is-invalid");
            }
            if (companyInput.value === "") {
                companyInput.style.border = "1px solid red";
            }
            else {
                companyInput.style.border = "1px solid rgba(146, 146, 146, 0.507)";
            }
            if (descriptionInput.value === "") {
                descriptionInput.classList.add("is-invalid");
            }
            else {
                descriptionInput.classList.remove("is-invalid");
            }
            if (idAuthorArray.length === 0) {
                authorInput.style.border = "1px solid red";
            }
            else {
                authorInput.style.border = "1px solid rgba(146, 146, 146, 0.507)";
            }
            if (idCategoryArray.length === 0) {
                categoryInput.style.border = "1px solid red";
            }
            else {
                categoryInput.style.border = "1px solid rgba(146, 146, 146, 0.507)";
            }
            if (imageInput.files[0] === undefined) {
                imageInput.classList.add("is-invalid");
            }
            else {
                imageInput.classList.remove("is-invalid");
            }
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
        });
    }
    deleteProductListener() {
        document.getElementById("buttonDeleteProductModal").addEventListener("click", () => {
            let element = document.getElementById("ProductOptionsDelete");
            let idArray = [];
            let elementArray;
            element.querySelectorAll(`#productCheckedBoxDelete`).forEach(element => {
                elementArray = element;
                if (elementArray.checked) {
                    idArray.push(elementArray.value);
                }
            });
            idArray.forEach(element => {
                fetch('http://localhost:8080/api/book', {
                    method: 'DELETE',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                    },
                    body: JSON.stringify({
                        id: `${element}`,
                    })
                });
            });
            document.getElementById("closeButtonCompanyDelete").click();
            window.location.reload();
        });
    }
    updateProductListener() {
        document.getElementById("buttonUpdateProductView").addEventListener("click", () => {
            let element = document.getElementById("selectProductOptions");
            let newTitle = null;
            let newYearLaunch = null;
            let newPage = null;
            let newPrice = null;
            let newInventory = null;
            // let  newImage = null;
            let newDescription = null;
            if (document.getElementById("newTitleInput").value != "") {
                newTitle = document.getElementById("newTitleInput").value;
            }
            if (document.getElementById("newYearLaunchInput").value != "") {
                newYearLaunch = document.getElementById("newYearLaunchInput").value;
            }
            if (document.getElementById("newPageInput").value != "") {
                newPage = document.getElementById("newPageInput").value;
            }
            if (document.getElementById("newPriceInput").value != "") {
                newPrice = document.getElementById("newPriceInput").value;
            }
            if (document.getElementById("newInventoryInput").value != "") {
                newInventory = document.getElementById("newInventoryInput").value;
            }
            if (document.getElementById("newDescriptionInput").value != "") {
                newDescription = document.getElementById("newDescriptionInput").value;
            }
            fetch('http://localhost:8080/api/book', {
                method: 'PUT',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                },
                body: JSON.stringify({
                    id: `${element.value}`,
                    title: `${newTitle}`,
                    yearLaunch: `${newYearLaunch}`,
                    pages: `${newPage}`,
                    price: `${newPrice}`,
                    inventory: {
                        amount: `${newInventory}`
                    },
                    description: `${newDescription}`,
                })
            });
            window.location.assign("/#/Profile");
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
