"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProfileApi = void 0;
class ProfileApi {
    isAdmin() {
        let isAdmin = window.localStorage.getItem("isAdmin");
        if (isAdmin === 'true') {
            return true;
        }
        return false;
    }
    getInfo() {
        let url = "/admin/find-by-username";
        let usernameLocal = window.localStorage.getItem("username");
        if (!this.isAdmin()) {
            url = "/user/find-by-username";
        }
        let logged = fetch('http://localhost:8080/api' + url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: usernameLocal
            })
        }).then(apiResponse => {
            apiResponse;
            // if(apiResponse.status === 200) {
            //     errorMensage.style.cssText = "display: none";
            //     usernameInput.classList.remove("is-invalid");
            //     passwordInput.classList.remove("is-invalid");
            //     apiResponse.json().then(content => {
            //         window.localStorage.setItem("token", content["token"]);
            //         window.localStorage.setItem("isAdmin", content["admin"]);
            //         window.localStorage.setItem("username", usernameInput.value);
            //     })
            //     closeButton.click();
            //     return true;
            // } else if(apiResponse.status === 400) {
            //     errorMensage.style.cssText = "";
            //     usernameInput.classList.add("is-invalid");
            //     passwordInput.classList.add("is-invalid");
            //     return false;
            // }
        });
        logged;
    }
}
exports.ProfileApi = ProfileApi;
