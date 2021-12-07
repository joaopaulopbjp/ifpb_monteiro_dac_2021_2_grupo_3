class CompanyService {
    registerButtonListener() {
        document.getElementById("registerButtonCompany")
        .addEventListener("click", () => {
            let companyNameInput = document.getElementById("nameInputCompany") as HTMLInputElement;
            let closeButtonCompany = document.getElementById("closeButtonCompany") as HTMLInputElement;

            fetch('http://localhost:8080/api/company', {
                method: 'POST',
                headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                },
                body: JSON.stringify({ 
                    name: `${companyNameInput.value}`,
                })
            }).then(apiResponse => {
                if(apiResponse.status === 201) {
                    companyNameInput.classList.remove("is-invalid");
                    closeButtonCompany.click();

                } else if(apiResponse.status === 400) {
                    companyNameInput.classList.add("is-invalid");
                }
            });
        });
    }

    deleteCompanyListener() {
        document.getElementById("buttonDeleteCompanyModal").addEventListener("click",() => {
            let element = document.getElementById("companyOptions");
            let idArray : Array<Number> = [];
            let elementArray;
            element.querySelectorAll(`#companyCheckedBoxDelete`).forEach(element => {
                elementArray = element as HTMLInputElement;
                if(elementArray.checked) {
                    idArray.push(elementArray.value);
                }
            });

            idArray.forEach(element => {
                fetch('http://localhost:8080/api/company', {
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
            document.getElementById("closeButtonCompanyDelete").click();
            window.location.reload();
        })
    }

    updateCompanyListener() {
        document.getElementById("buttonUpdateCompanyModal").addEventListener("click", () => {
            let element = document.getElementById("companySelectOptions") as HTMLInputElement;
            let newName = document.getElementById("newNameCompany") as HTMLInputElement;

            fetch('http://localhost:8080/api/company', {
                            method: 'PUT',
                            headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                            },
                            body: JSON.stringify({
                                id: `${element.value}`,
                                name: `${newName.value}`,
                            })
            })
                document.getElementById("closeButtonCompanyUpdate").click();
                window.location.reload();
        })
    }
}
export { CompanyService };