"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.LoginApi = void 0;
class LoginApi {
    login() {
        let usernameInput = document.getElementById("usernameInput");
        let passwordInput = document.getElementById("passwordInput");
        let errorMensage = document.getElementById("error-mensage");
        fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: usernameInput.value,
                password: passwordInput.value
            })
        }).then(apiResponse => {
            if (apiResponse.status === 200) {
                errorMensage.style.cssText = "display: none";
                usernameInput.classList.remove("is-invalid");
                passwordInput.classList.remove("is-invalid");
                apiResponse.json().then(content => {
                    window.localStorage.setItem("token", content["response"]);
                    window.localStorage.setItem("username", usernameInput.value);
                });
            }
            else if (apiResponse.status === 400) {
                errorMensage.style.cssText = "";
                usernameInput.classList.add("is-invalid");
                passwordInput.classList.add("is-invalid");
            }
        });
    }
}
exports.LoginApi = LoginApi;
