class LoginApi {
    login() {

        let usernameInput =  document.getElementById("usernameInput") as HTMLInputElement;
        let passwordInput = document.getElementById("passwordInput") as HTMLInputElement;

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
            if(apiResponse.status === 200) {
                apiResponse.json().then(content => {
                    window.localStorage.setItem("token", content["response"]);
                    window.localStorage.setItem("username", usernameInput.value);

                    alert(window.localStorage.getItem("token"));
                    alert(window.localStorage.getItem("username"));
                })

            } else if(apiResponse.status === 401) {
                alert("401");
            }
        })
        
    }
}
export { LoginApi };