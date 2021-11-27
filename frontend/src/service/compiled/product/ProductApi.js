"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProductApi = void 0;
const Base64_1 = require("../utils/Base64");
class ProductApi {
    saveEvent() {
        // let base64 = new Base64();
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
                    }).then(response => {
                        alert(response.status);
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
