class SideBarApi {
    isAdmin() {
        let isAdmin = window.localStorage.getItem("isAdmin");

        if(isAdmin === 'true'){
            return true;
        }
        return false;
    }
    
    showImage() {
        let url = "/admin/find-by-username";
        let usernameLocal = window.localStorage.getItem("username");

        if(!this.isAdmin()) {
            url = "/user/find/find-by-username";
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
        }).then(async function(apiResponse) {
            let divProfile = document.getElementById("profileImage");

            let json = await apiResponse.json();
            if(json['image'] !== undefined) {
                divProfile.innerHTML = 
                    `<img src="${json["image"]}" width="150vw" height="150vw" style="border-radius: 50%"/>`
            } else {
                divProfile.innerHTML = 
                    '<i class="fas fa-user-circle fa-5x" style="color: #343A40;"></i>'
            }
        });
    }
}
export { SideBarApi }