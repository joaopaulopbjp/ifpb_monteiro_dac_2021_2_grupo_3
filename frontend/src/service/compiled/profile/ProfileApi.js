"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProfileApi = void 0;
const Sha_1 = require("../utils/Sha");
const LoginApi_1 = require("../login/LoginApi");
const Base64_1 = require("../utils/Base64");
class ProfileApi {
    isAdmin() {
        let isAdmin = window.localStorage.getItem("isAdmin");
        if (isAdmin === 'true') {
            return true;
        }
        return false;
    }
    getInfoUser() {
        let url = "/admin/find-by-username";
        let usernameLocal = window.localStorage.getItem("username");
        if (!this.isAdmin()) {
            url = "/user/find/find-by-username";
        }
        return fetch('http://localhost:8080/api' + url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${window.localStorage.getItem("token")}`
            },
            body: JSON.stringify({
                username: usernameLocal
            })
        }).then(apiResponse => {
            return apiResponse;
        });
    }
    setInfoOnVue() {
        let emailInput = document.getElementById("emailInput");
        let usernameInput = document.getElementById("usernameInput");
        this.getInfoUser().then(async (response) => {
            let json = await response.json();
            usernameInput.value = json["username"];
            emailInput.value = json["email"];
        });
    }
    editButtonEvent() {
        let editButton = document.getElementById("editButton");
        let emailInput = document.getElementById("emailInput");
        let usernameInput = document.getElementById("usernameInput");
        let oldPasswordInput = document.getElementById("oldPasswordInput");
        let newPasswordInput = document.getElementById("newPasswordInput");
        let confirmNewPasswordInput = document.getElementById("confirmNewPasswordInput");
        let imageInput = document.getElementById("imageInput");
        let saveButton = document.getElementById("saveButton");
        editButton.addEventListener("click", () => {
            if (editButton.value === "disabled") {
                emailInput.attributes.removeNamedItem("disabled");
                usernameInput.attributes.removeNamedItem("disabled");
                oldPasswordInput.attributes.removeNamedItem("disabled");
                newPasswordInput.attributes.removeNamedItem("disabled");
                confirmNewPasswordInput.attributes.removeNamedItem("disabled");
                imageInput.attributes.removeNamedItem("disabled");
                saveButton.style.display = "inline";
                editButton.value = "enabled";
            }
            else {
                emailInput.setAttribute("disabled", "");
                usernameInput.setAttribute("disabled", "");
                oldPasswordInput.setAttribute("disabled", "");
                newPasswordInput.setAttribute("disabled", "");
                confirmNewPasswordInput.setAttribute("disabled", "");
                imageInput.setAttribute("disabled", "");
                saveButton.style.display = "none";
                this.setInfoOnVue();
                editButton.value = "disabled";
            }
        });
    }
    saveButtonEvent() {
        let saveButton = document.getElementById("saveButton");
        let sha = new Sha_1.Sha();
        let base64 = new Base64_1.Base64();
        saveButton.addEventListener("click", () => {
            let json = this.getInfoUser();
            let password = json['password'];
            let emailInput = document.getElementById("emailInput");
            let usernameInput = document.getElementById("usernameInput");
            let oldPasswordInput = document.getElementById("oldPasswordInput");
            let newPasswordInput = document.getElementById("newPasswordInput");
            let confirmNewPasswordInput = document.getElementById("confirmNewPasswordInput");
            let imageInput = document.getElementById("imageInput");
            let passwordVar = null;
            let imageVar = null;
            let flag = true;
            if (emailInput.value === "") {
                emailInput.classList.add("is-invalid");
                flag = false;
            }
            if (usernameInput.value === "") {
                usernameInput.classList.add("is-invalid");
                flag = false;
            }
            if (oldPasswordInput.value !== "" && sha.sha256(oldPasswordInput.value) !== password) {
                oldPasswordInput.classList.add("is-invalid");
                flag = false;
            }
            if (newPasswordInput.value !== "" && newPasswordInput.value !== confirmNewPasswordInput.value) {
                newPasswordInput.classList.add("is-invalid");
                confirmNewPasswordInput.classList.add("is-invalid");
                passwordVar = newPasswordInput.value;
                flag = false;
            }
            if (imageInput.files[0] !== undefined) {
                base64.urlToBase64(imageInput.files[0]).then(async function (image) {
                    imageVar = await image;
                }).then(() => {
                    if (flag) {
                        let url = "/admin";
                        if (!this.isAdmin()) {
                            url = "/user";
                        }
                        fetch('http://localhost:8080/api' + url, {
                            method: 'PUT',
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json',
                                "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                            },
                            body: JSON.stringify({
                                username: usernameInput.value,
                                email: emailInput.value,
                                image: imageVar,
                                password: passwordVar
                            })
                        }).then(response => {
                            if (response.ok) {
                                let login = new LoginApi_1.LoginApi();
                                login.logout();
                            }
                            else {
                                this.setInfoOnVue();
                            }
                        });
                    }
                });
            }
            if (flag) {
                let url = "/admin";
                if (!this.isAdmin()) {
                    url = "/user";
                }
                fetch('http://localhost:8080/api' + url, {
                    method: 'PUT',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                    },
                    body: JSON.stringify({
                        username: usernameInput.value,
                        email: emailInput.value,
                        image: imageVar,
                        password: passwordVar
                    })
                }).then(response => {
                    if (response.ok) {
                        let login = new LoginApi_1.LoginApi();
                        login.logout();
                    }
                    else {
                        this.setInfoOnVue();
                    }
                });
            }
        });
    }
    addAddressOnVue() {
        fetch('http://localhost:8080/api/addressTh/address', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${window.localStorage.getItem("token")}`
            }
        }).then(async (apiResponse) => {
            if (apiResponse.status === 200) {
                let addressDiv = document.getElementById("addressDiv");
                addressDiv.innerHTML = await apiResponse.text();
                addressDiv.querySelectorAll('[id=editButtonAddress]').forEach(element => {
                    element;
                    let input = element;
                    let streetAddress = document.getElementById(`streetAdress${input.name}`);
                    let numberAdress = document.getElementById(`numberAdress${input.name}`);
                    let cityAdress = document.getElementById(`cityAdress${input.name}`);
                    let zipcodeAdress = document.getElementById(`zipcodeAdress${input.name}`);
                    let districtAdress = document.getElementById(`districtAdress${input.name}`);
                    let savebutton = document.getElementById(`savebuttonAddress${input.name}`);
                    // let trashbutton = document.getElementById(`trashbuttonAddress${input.name}`);
                    element.addEventListener("click", () => {
                        if (element.value === "disabled") {
                            streetAddress.attributes.removeNamedItem("disabled");
                            numberAdress.attributes.removeNamedItem("disabled");
                            cityAdress.attributes.removeNamedItem("disabled");
                            zipcodeAdress.attributes.removeNamedItem("disabled");
                            districtAdress.attributes.removeNamedItem("disabled");
                            savebutton.style.display = "inline";
                            element.value = "enabled";
                        }
                        else {
                            streetAddress.setAttribute("disabled", "");
                            numberAdress.setAttribute("disabled", "");
                            cityAdress.setAttribute("disabled", "");
                            zipcodeAdress.setAttribute("disabled", "");
                            districtAdress.setAttribute("disabled", "");
                            savebutton.style.display = "none";
                            this.addAddressOnVue();
                            element.value = "disabled";
                        }
                    });
                    // savebutton.addEventListener("click", () => {
                    //     fetch('http://localhost:8080/api/address', {
                    //         method: 'PUT',
                    //         headers: {
                    //         'Accept': 'application/json',
                    //         'Content-Type': 'application/json',
                    //         'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                    //         },
                    //         body: JSON.stringify({
                    //             id: `${input.name}`,
                    //             street: `${(streetAddress as HTMLInputElement).value}`,
                    //             number: `${(numberAdress as HTMLInputElement).value}`,
                    //             zipCode: `${(zipcodeAdress as HTMLInputElement).value}`,
                    //             city: `${(cityAdress as HTMLInputElement).value}`,
                    //             district: `${(districtAdress as HTMLInputElement).value}`
                    //         })
                    //     }).then(apiResponse => {
                    //         if(apiResponse.status === 200) {
                    //             this.addAddressOnVue();
                    //         } else if(apiResponse.status === 400) {
                    //             streetAddress.classList.add("is-invalid");
                    //             numberAdress.classList.add("is-invalid");
                    //             cityAdress.classList.add("is-invalid");
                    //             zipcodeAdress.classList.add("is-invalid");
                    //             districtAdress.classList.add("is-invalid");
                    //         }
                    //     });
                    // })
                    // trashbutton.addEventListener("click", () => {
                    //     fetch('http://localhost:8080/api/address', {
                    //         method: 'DELETE',
                    //         headers: {
                    //         'Accept': 'application/json',
                    //         'Content-Type': 'application/json',
                    //         'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                    //         },
                    //         body: JSON.stringify({
                    //             id: `${input.name}`
                    //         })
                    //     }).then(apiResponse => {
                    //         if(apiResponse.status === 200) {
                    //             this.addAddressOnVue();
                    //         }
                    //     });
                    // })
                });
            }
        });
    }
    addRequestOnVue() {
        if (window.localStorage.getItem("isAdmin") === 'true')
            return;
        fetch('http://localhost:8080/api/order/find-all', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${window.localStorage.getItem("token")}`
            }
        }).then(async (apiResponse) => {
            if (apiResponse.status === 200) {
                let json = await apiResponse.json();
                let requestprofileDiv = document.getElementById("requestprofile");
                let html = "";
                json.forEach(element => {
                    element["itemList"].forEach(itemList => {
                        html += `
                        <div class="border border-dark rounded text-center m-2 p-2" style="background-color: #D1B1E8; width:15vw;">
                            <h5>${itemList["product"]["title"]}</h5>
                            <button id="buttonImageprofile" value="${itemList["product"]["id"]}" style=" background: transparent; border: none !important; font-size:0;"><img class="mt-2 mb-2" style="height: 25vh; width:10vw;" src="${itemList["product"]["imageList"][0]["base64"]}" alt=""></button> 
                            <h5>Amount: ${itemList["amount"]}</h5>
                            <h5>Total: R$ ${itemList["totalPrice"]}</h5>
                        </div>
                        `;
                    });
                });
                requestprofileDiv.innerHTML = html;
                requestprofileDiv.querySelectorAll('[id=buttonImageprofile]').forEach(element => {
                    element;
                    element.addEventListener("click", () => {
                        window.localStorage.setItem("productId", element.attributes.getNamedItem("value").value);
                        window.location.replace("http://localhost:8081/#/product");
                    });
                });
            }
        });
    }
}
exports.ProfileApi = ProfileApi;
