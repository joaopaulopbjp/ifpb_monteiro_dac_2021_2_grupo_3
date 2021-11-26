"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProductApi = void 0;
class ProductApi {
    saveEvent() {
        // let base64 = new Base64();
        let savebutton = document.getElementById("saveButton");
        // let titleInput = document.getElementById("titleInput") as HTMLInputElement;
        // let yearLaunchInput = document.getElementById("yearLaunchInput") as HTMLInputElement;
        // let pageInput = document.getElementById("pageInput") as HTMLInputElement;
        // let priceInput = document.getElementById("priceInput") as HTMLInputElement;
        // let inventoryInput = document.getElementById("inventoryInput") as HTMLInputElement;
        // let companyInput = document.getElementById("companyInput") as HTMLInputElement;
        // let imageInput = document.getElementById("imageInput") as HTMLInputElement;
        let authorsInput = document.getElementById("authorsInput");
        let descriptionInput = document.getElementById("descriptionInput");
        let categoriesInput = document.getElementById("categoriesInput");
        savebutton.addEventListener("click", async () => {
            authorsInput;
            descriptionInput;
            categoriesInput;
            alert(document.getElementById("checkbox"));
            alert(document.querySelectorAll(".checkbox").length);
            //     fetch('http://localhost:8080/api/book', {
            //     method: 'POST',
            //     headers: {
            //     'Accept': 'application/json',
            //     'Content-Type': 'application/json'
            //     },
            //     body: JSON.stringify({
            //         title: titleInput.value,
            //         yearLaunch: yearLaunchInput.value,
            //         pages: pageInput.value,
            //         price: priceInput.value,
            //         inventory: {
            //             amount: inventoryInput.value
            //         },
            //         idCompany: companyInput.value,
            //         imageList: [{
            //             base64: base64.urlToBase64(imageInput.value)
            //         }],
            //         idAuthorList: []
            //     })
            // }).then(apiResponse => {
            //     if(apiResponse.status === 200) {
            //         errorMensage.style.cssText = "display: none";
            //         usernameInput.classList.remove("is-invalid");
            //         passwordInput.classList.remove("is-invalid");
            //         apiResponse.json().then(content => {
            //             window.localStorage.setItem("token", content["token"]);
            //             window.localStorage.setItem("isAdmin", content["admin"]);
            //             window.localStorage.setItem("username", usernameInput.value);
            //         })
            //         closeButton.click();
            //         return true;
            //     } else if(apiResponse.status === 400) {
            //         errorMensage.style.cssText = "";
            //         usernameInput.classList.add("is-invalid");
            //         passwordInput.classList.add("is-invalid");
            //         return false;
            //     }
            // });
        });
    }
}
exports.ProductApi = ProductApi;
