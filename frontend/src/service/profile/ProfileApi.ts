import { Sha } from '../utils/sha.js';
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
            url = "/user/find-by-username";
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

        editButton.addEventListener("click", () => {
            if((editButton as HTMLInputElement).value === "disabled") {
                emailInput.attributes.removeNamedItem("disabled");
                usernameInput.attributes.removeNamedItem("disabled");
                oldPasswordInput.attributes.removeNamedItem("disabled");
                newPasswordInput.attributes.removeNamedItem("disabled");
                confirmNewPasswordInput.attributes.removeNamedItem("disabled");

                (editButton as HTMLInputElement).value = "enabled";
                
            } else {
                emailInput.setAttribute("disabled", "");
                usernameInput.setAttribute("disabled", "");
                oldPasswordInput.setAttribute("disabled", "");
                newPasswordInput.setAttribute("disabled", "");
                confirmNewPasswordInput.setAttribute("disabled", "");

                this.setInfoOnVue();

                (editButton as HTMLInputElement).value = "disabled";
            }
        });
    }

    saveButtonEvent() {
        let saveButton = document.getElementById("saveButton");
        let sha = new Sha();

        let json = this.getInfoUser();
        let password = json['password'];
        
        saveButton.addEventListener("click", () => {
            let emailInput = document.getElementById("emailInput") as HTMLInputElement;
            let usernameInput = document.getElementById("usernameInput") as HTMLInputElement;
            let oldPasswordInput = document.getElementById("oldPasswordInput") as HTMLInputElement;
            let newPasswordInput = document.getElementById("newPasswordInput") as HTMLInputElement;
            let confirmNewPasswordInput = document.getElementById("confirmNewPasswordInput") as HTMLInputElement;
            
            let passwordVar = null;


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
                        password: passwordVar
                    })
                }).then(async reponse => {
                    this.setInfoOnVue();
                    let json = await reponse.json();
                    fetch('http://localhost:8080/api/login', {
                        method: 'POST',
                        headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            username: json["username"],
                            password: json["password"]
                        })
                    }).then(apiResponse => {
                        if(apiResponse.status === 200) {
                            apiResponse.json().then(content => {
                                window.localStorage.setItem("token", content["token"]);
                                window.localStorage.setItem("isAdmin", content["admin"]);
                                window.localStorage.setItem("username", usernameInput.value);

                                let usernameSideBar = document.getElementById("sidebar-no-header-title");
                                usernameSideBar.innerText = usernameInput.value;
                            })
                        }
                    });
                    let editButton = document.getElementById("editButton");
                    if((editButton as HTMLInputElement).value === "enabled")
                        editButton.click();
                });
            }
        })
    }
}

export { ProfileApi };