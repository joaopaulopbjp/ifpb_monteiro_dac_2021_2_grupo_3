import { Sha } from '../utils/Sha';
import { LoginApi } from '../login/LoginApi';
import { Base64 } from '../utils/Base64';
import { AddressService } from '../address/AddressService';
class ProfileApi {

    isAdmin() {
        let isAdmin = window.localStorage.getItem("isAdmin");

        if(isAdmin === 'true'){
            return true;
        }
        return false;
    }

    getInfoUser() {
        let url = "/admin/find-by-username";
        let usernameLocal = window.localStorage.getItem("username");

        if(!this.isAdmin()) {
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
        let emailInput = document.getElementById("emailInput") as HTMLInputElement;
        let usernameInput = document.getElementById("usernameInput") as HTMLInputElement;

        this.getInfoUser().then(async response => {
            let json = await response.json();
            usernameInput.value = json["username"];
            emailInput.value = json["email"];
        })
    }

    editButtonEvent() {
        let editButton = document.getElementById("editButton");

        let emailInput = document.getElementById("emailInput") as HTMLInputElement;
        let usernameInput = document.getElementById("usernameInput") as HTMLInputElement;
        let oldPasswordInput = document.getElementById("oldPasswordInput") as HTMLInputElement;
        let newPasswordInput = document.getElementById("newPasswordInput") as HTMLInputElement;
        let confirmNewPasswordInput = document.getElementById("confirmNewPasswordInput") as HTMLInputElement;
        let imageInput = document.getElementById("imageInput") as HTMLInputElement;

        let saveButton = document.getElementById("saveButton");

        editButton.addEventListener("click", () => {
            if((editButton as HTMLInputElement).value === "disabled") {
                emailInput.attributes.removeNamedItem("disabled");
                usernameInput.attributes.removeNamedItem("disabled");
                oldPasswordInput.attributes.removeNamedItem("disabled");
                newPasswordInput.attributes.removeNamedItem("disabled");
                confirmNewPasswordInput.attributes.removeNamedItem("disabled");
                imageInput.attributes.removeNamedItem("disabled");

                saveButton.style.display = "inline";

                (editButton as HTMLInputElement).value = "enabled";
                
            } else {
                emailInput.setAttribute("disabled", "");
                usernameInput.setAttribute("disabled", "");
                oldPasswordInput.setAttribute("disabled", "");
                newPasswordInput.setAttribute("disabled", "");
                confirmNewPasswordInput.setAttribute("disabled", "");
                imageInput.setAttribute("disabled", "");

                saveButton.style.display = "none";

                this.setInfoOnVue();

                (editButton as HTMLInputElement).value = "disabled";
            }
        });
    }

    saveButtonEvent() {
        let saveButton = document.getElementById("saveButton");
        let sha = new Sha();
        let base64 = new Base64();

        saveButton.addEventListener("click", () => {
            let json = this.getInfoUser();
            let password = json['password'];

            let emailInput = document.getElementById("emailInput") as HTMLInputElement;
            let usernameInput = document.getElementById("usernameInput") as HTMLInputElement;
            let oldPasswordInput = document.getElementById("oldPasswordInput") as HTMLInputElement;
            let newPasswordInput = document.getElementById("newPasswordInput") as HTMLInputElement;
            let confirmNewPasswordInput = document.getElementById("confirmNewPasswordInput") as HTMLInputElement;
            let imageInput = document.getElementById("imageInput") as HTMLInputElement;
            
            let passwordVar = null;
            let imageVar = null;


            let flag = true;
            if(emailInput.value === "") {
                emailInput.classList.add("is-invalid");
                flag = false;
            }
            if(usernameInput.value === "") {
                usernameInput.classList.add("is-invalid");
                flag = false;
            }
                
            if(oldPasswordInput.value !== "" && sha.sha256(oldPasswordInput.value) !== password) {
                oldPasswordInput.classList.add("is-invalid");
                flag = false;
            }
            if(newPasswordInput.value !== "" && newPasswordInput.value !== confirmNewPasswordInput.value) {
                newPasswordInput.classList.add("is-invalid");
                confirmNewPasswordInput.classList.add("is-invalid");
                passwordVar = newPasswordInput.value;
                flag = false;
            }

            
            if(imageInput.files[0] !== undefined) {
                base64.urlToBase64(imageInput.files[0]).then(async function(image) {
                    imageVar = await image;
                }).then(() => {
                    if(flag) {
                        let url = "/admin";
                        if(!this.isAdmin()) {
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
                            if(response.ok) {
                                let login = new LoginApi();
                                login.logout();
                            } else {
                                this.setInfoOnVue();
                            }
                        });
                    }
                })
            }
            if(flag) {
                let url = "/admin";
                if(!this.isAdmin()) {
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
                    if(response.ok) {
                        let login = new LoginApi();
                        login.logout();
                    } else {
                        this.setInfoOnVue();
                    }
                });
            }
        })
    }

    addAddressOnVue() {
        let addressService = new AddressService();
        addressService.getAddressInfo().then(async (response) => {
            if(response !== null) {
                let json = await response.json();
                let addressDiv = document.getElementById("addressDiv");
                let html = '';

                json.forEach(element => {
                    html += `
                    <div class="border border-dark rounded text-center ml-3 mt-3 p-3" style="background-color: #D1B1E8; width: 280px;">
                            <abbr title="street"><input style=" background: transparent; border: none !important; padding: 3px;" type="text" id="streetAdress${element["id"]}" disabled value='${element["street"]}'></abbr>
                            <abbr title="number"><input style=" background: transparent; border: none !important; padding: 3px;" type="text" id="numberAdress${element["id"]}" disabled value='${element["number"]}'></abbr>
                            <abbr title="city"><input style=" background: transparent; border: none !important; padding: 3px;" type="text" id="cityAdress${element["id"]}" disabled value='${element["city"]}'></abbr>
                            <abbr title="zipCode"><input style=" background: transparent; border: none !important; padding: 3px;" type="text" id="zipcodeAdress${element["id"]}" disabled value='${element["zipCode"]}'></abbr>
                            <abbr title="district"><input style=" background: transparent; border: none !important; padding: 3px;" type="text" id="districtAdress${element["id"]}" disabled value='${element["district"]}'></abbr>
                            <div class="d-flex justify-content-end mt-2">
                                <button id="editButtonAddress" class="style-btn-yellow" name='${element["id"]}' value="disabled" style="margin-left: 3px;"><i class="fas fa-pen"></i></button>
                                <button class="style-btn-dark" id='trashbuttonAddress${element["id"]}' style="margin-left: 3px;"><i class="fas fa-trash-alt"></i></button>
                                <button id='savebuttonAddress${element["id"]}' class="style-btn-green" name='${element["id"]}' style="margin-left: 3px; display: none;"><i class="far fa-save"></i></button>
                            </div>
                        </div>
                    `
                });
                addressDiv.innerHTML = html;

                addressDiv.querySelectorAll('[id=editButtonAddress]').forEach(element => {
                    element;
                    let input = element as HTMLInputElement;

                    let streetAddress = document.getElementById(`streetAdress${input.name}`);
                    let numberAdress = document.getElementById(`numberAdress${input.name}`);
                    let cityAdress = document.getElementById(`cityAdress${input.name}`);
                    let zipcodeAdress = document.getElementById(`zipcodeAdress${input.name}`);
                    let districtAdress = document.getElementById(`districtAdress${input.name}`);

                    let savebutton = document.getElementById(`savebuttonAddress${input.name}`);
                    let trashbutton = document.getElementById(`trashbuttonAddress${input.name}`);

                    (element as HTMLButtonElement).addEventListener("click", () => {
                        if((element as HTMLInputElement).value === "disabled") {
                            streetAddress.attributes.removeNamedItem("disabled");
                            numberAdress.attributes.removeNamedItem("disabled");
                            cityAdress.attributes.removeNamedItem("disabled");
                            zipcodeAdress.attributes.removeNamedItem("disabled");
                            districtAdress.attributes.removeNamedItem("disabled");
            
                            savebutton.style.display = "inline";
            
                            (element as HTMLInputElement).value = "enabled";
                            
                        } else {
                            streetAddress.setAttribute("disabled", "");
                            numberAdress.setAttribute("disabled", "");
                            cityAdress.setAttribute("disabled", "");
                            zipcodeAdress.setAttribute("disabled", "");
                            districtAdress.setAttribute("disabled", "");
            
                            savebutton.style.display = "none";
            
                            this.addAddressOnVue();
            
                            (element as HTMLInputElement).value = "disabled";
                        }
                    })

                    savebutton.addEventListener("click", () => {

                        fetch('http://localhost:8080/api/address', {
                            method: 'PUT',
                            headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                            },
                            body: JSON.stringify({
                                id: `${input.name}`,
                                street: `${(streetAddress as HTMLInputElement).value}`,
                                number: `${(numberAdress as HTMLInputElement).value}`,
                                zipCode: `${(zipcodeAdress as HTMLInputElement).value}`,
                                city: `${(cityAdress as HTMLInputElement).value}`,
                                district: `${(districtAdress as HTMLInputElement).value}`
                            })
                        }).then(apiResponse => {
                            if(apiResponse.status === 200) {
                                this.addAddressOnVue();
                            } else if(apiResponse.status === 400) {
                                streetAddress.classList.add("is-invalid");
                                numberAdress.classList.add("is-invalid");
                                cityAdress.classList.add("is-invalid");
                                zipcodeAdress.classList.add("is-invalid");
                                districtAdress.classList.add("is-invalid");
                            }
                        });
                    })

                    trashbutton.addEventListener("click", () => {

                        fetch('http://localhost:8080/api/address', {
                            method: 'DELETE',
                            headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                            },
                            body: JSON.stringify({
                                id: `${input.name}`
                            })
                        }).then(apiResponse => {
                            if(apiResponse.status === 200) {
                                this.addAddressOnVue();
                            }
                        });
                    })
                  });
            }
        })
    }

    addRequestOnVue() {
        fetch('http://localhost:8080/api/order/find-all', {
            method: 'GET',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${window.localStorage.getItem("token")}`
            }
        }).then(async apiResponse => {
            if(apiResponse.status === 200) {
                let json = await apiResponse.json();
                let requestprofileDiv = document.getElementById("requestprofile");
                let html = "";

                json.forEach(element => {
                    element["itemList"].forEach(itemList => {
                        html += `
                        <div class="border border-dark rounded text-center p-2" style="background-color: #D1B1E8; width: 250px;">
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
                    (element as HTMLButtonElement).addEventListener("click", () => {
                      window.localStorage.setItem("productId", element.attributes.getNamedItem("value").value);
                      window.location.replace("http://localhost:8081/#/product");
                    })
                });

            }
        });
    }
}

export { ProfileApi };