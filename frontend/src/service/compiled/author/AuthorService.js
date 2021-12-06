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
    deleteAuthorListener() {
        document.getElementById("buttonDeleteAuthor").addEventListener("click", () => {
            let idAuthorArray = this.getIdListFromCheckboxId(document.getElementById("authorCheckedBoxDelete"));
            alert("elewrglkwrngt");
            idAuthorArray.forEach(element => {
                fetch('http://localhost:8080/api/author', {
                    method: 'DELETE',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                    },
                    body: JSON.stringify({
                        id: `${element}`,
                    })
                });
            });
            document.getElementById("closeButtonAuthor").click();
        });
    }
    getIdListFromCheckboxId(id) {
        let idArray = [];
        let elementArray;
        document.querySelectorAll(`#${id}`).forEach(element => {
            elementArray = element;
            if (elementArray.checked) {
                idArray.push(elementArray.value);
            }
        });
        return idArray;
    }
}
exports.AuthorService = AuthorService;
