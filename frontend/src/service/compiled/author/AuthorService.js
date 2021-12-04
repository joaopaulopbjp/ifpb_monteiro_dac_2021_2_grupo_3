"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.AuthorService = void 0;
class AuthorService {
    registerButtonListener() {
        document.getElementById("registerButtonAuthor")
            .addEventListener("click", () => {
            let authorNameInput = document.getElementById("nameInputAuthor");
            let closeButtonAuthor = document.getElementById("closeButtonAuthor");
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
                if (apiResponse.status === 201) {
                    authorNameInput.classList.remove("is-invalid");
                    closeButtonAuthor.click();
                }
                else if (apiResponse.status === 400) {
                    authorNameInput.classList.add("is-invalid");
                }
            });
        });
    }
}
exports.AuthorService = AuthorService;
