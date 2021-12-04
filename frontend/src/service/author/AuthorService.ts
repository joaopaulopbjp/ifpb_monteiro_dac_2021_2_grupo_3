class AuthorService {
    registerButtonListener() {
        document.getElementById("registerButtonAuthor")
        .addEventListener("click", () => {
            let authorNameInput = document.getElementById("nameInputAuthor") as HTMLInputElement;
            let closeButtonAuthor = document.getElementById("closeButtonAuthor") as HTMLInputElement;

            fetch('http://localhost:8080/api/author', {
                method: 'POST',
                headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                },
                body: JSON.stringify({
                    name: `${authorNameInput.value}`,
                })
            }).then(apiResponse => {
                if(apiResponse.status === 201) {
                    authorNameInput.classList.remove("is-invalid");
                    closeButtonAuthor.click();

                } else if(apiResponse.status === 400) {
                    authorNameInput.classList.add("is-invalid");
                }
            });
        });
    }
}
export { AuthorService };