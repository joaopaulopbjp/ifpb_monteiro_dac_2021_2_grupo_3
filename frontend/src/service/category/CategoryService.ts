class CategoryService {
    registerButtonListener() {
        document.getElementById("registerButtonCategory")
        .addEventListener("click", () => {
            let categoryNameInput = document.getElementById("nameInputCategory") as HTMLInputElement;
            let closeButtonCategory = document.getElementById("closeButtonCategory") as HTMLInputElement;

            fetch('http://localhost:8080/api/category', {
                method: 'POST',
                headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                },
                body: JSON.stringify({
                    name: `${categoryNameInput.value}`,
                })
            }).then(apiResponse => {
                if(apiResponse.status === 201) {
                    categoryNameInput.classList.remove("is-invalid");
                    closeButtonCategory.click();

                } else if(apiResponse.status === 400) {
                    categoryNameInput.classList.add("is-invalid");
                }
            });
        });
    }

    deleteCategoryListener() {
            document.getElementById("buttonDeleteCategoryModal").addEventListener("click", ()=> {
                    let element = document.getElementById("categoryOptions");
                    let idArray : Array<Number> = [];
                    let elementArray;
                    element.querySelectorAll(`#categoryCheckedBoxDelete`).forEach(element => {
                        elementArray = element as HTMLInputElement;
                        if(elementArray.checked) {
                            idArray.push(elementArray.value);
                        }
                    });
                    
                    idArray.forEach(element => {
                        fetch('http://localhost:8080/api/category', {
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
                    document.getElementById("closeButtonCategoryDelete").click();
                    window.location.reload();
            });
    }
}
export { CategoryService };