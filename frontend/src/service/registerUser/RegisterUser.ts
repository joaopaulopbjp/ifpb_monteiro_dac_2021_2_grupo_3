class RegisterUser {
    register() {
        let usernameInput =  document.getElementById("createName") as HTMLInputElement;
        let emailInput =  document.getElementById("createEmail") as HTMLInputElement;
        let passwordInput = document.getElementById("createPassword") as HTMLInputElement;

        let closeButton = document.getElementById("closeButtonRegister");

        fetch('http://localhost:8080/api/user/save', {
            method: 'POST',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: usernameInput.value,
                email: emailInput.value,
                password: passwordInput.value
            })
        }).then(apiResponse => {
            if(apiResponse.status === 201) {
                usernameInput.classList.remove("is-invalid");
                emailInput.classList.remove("is-invalid");
                passwordInput.classList.remove("is-invalid");

                closeButton.click();
            } else if(apiResponse.status === 400) {
                usernameInput.classList.add("is-invalid");
                emailInput.classList.add("is-invalid");
                passwordInput.classList.add("is-invalid");
            }
        });
    }
}
export { RegisterUser };