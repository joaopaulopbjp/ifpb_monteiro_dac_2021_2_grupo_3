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
                    })
                    
            });
            document.getElementById("closeButtonAuthor").click();


        })

    }

    private getIdListFromCheckboxId(id) {
        let idArray : Array<Number> = [];
        let elementArray
        document.querySelectorAll(`#${id}`).forEach(element => {
            elementArray = element as HTMLInputElement;
            if(elementArray.checked) {
                idArray.push(elementArray.value);
            }
        });
        return idArray;
    }
}
export { AuthorService };