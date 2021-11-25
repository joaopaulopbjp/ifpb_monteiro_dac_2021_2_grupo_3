class ProfileApi {
    isAdmin() {
        let isAdmin = window.localStorage.getItem("isAdmin");

        if(isAdmin === 'true'){
            return true;
        }
        return false;
    }

    getInfo() {
        let url = "/admin/find-by-username";
        let usernameLocal = window.localStorage.getItem("username");

        let emailInput = document.getElementById("emailInput") as HTMLInputElement;
        let usernameInput = document.getElementById("usernameInput") as HTMLInputElement;

        if(!this.isAdmin()) {
            url = "/user/find-by-username";
        }

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
        }).then(async apiResponse => {
            let json = await apiResponse.json();
            if(apiResponse.status === 200) {
                emailInput.value = json["email"];
                usernameInput.value = json["username"];
            }
        });
    }

    editButtonEvent() {
        let editButton = document.getElementById("editButton");

        let emailInput = document.getElementById("emailInput");
        let usernameInput = document.getElementById("usernameInput");
        let oldPasswordInput = document.getElementById("oldPasswordInput");
        let newPasswordInput = document.getElementById("newPasswordInput");
        let confirmNewPasswordInput = document.getElementById("confirmNewPasswordInput");

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

                this.getInfo();

                (editButton as HTMLInputElement).value = "disabled";
            }
        });
    }
}

export { ProfileApi };