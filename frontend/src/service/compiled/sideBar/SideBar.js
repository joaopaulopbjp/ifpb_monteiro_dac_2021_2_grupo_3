"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.SideBar = void 0;
class SideBar {
    isAdmin() {
        let isAdmin = window.localStorage.getItem("isAdmin");
        if (isAdmin === 'true') {
            return true;
        }
        return false;
    }
    vue() {
        let url = "/admin/find-by-username";
        let usernameLocal = window.localStorage.getItem("username");
        if (!this.isAdmin()) {
            url = "/user/find-by-username";
        }
        alert();
        fetch('http://localhost:8080/api' + url, {
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
            let divProfile = document.getElementById("profileImage");
            let json = apiResponse.json();
            alert(json);
            if (json['image'] !== null) {
                divProfile.innerHTML =
                    `<b-avatar variant="dark" src=${json['image']} size = 500px class="text-decoration-none mb-3" size="5rem"></b-avatar>`;
            }
            else {
                divProfile.innerHTML =
                    '<b-avatar variant="dark" text="PS" class="text-decoration-none mb-3" size="5rem"></b-avatar>';
            }
        });
    }
}
exports.SideBar = SideBar;
