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
}
export { CategoryService };