"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.CompanyService = void 0;
class CompanyService {
    registerButtonListener() {
        document.getElementById("registerButtonCompany")
            .addEventListener("click", () => {
            let companyNameInput = document.getElementById("nameInputCompany");
            let closeButtonCompany = document.getElementById("closeButtonCompany");
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
                if (apiResponse.status === 201) {
                    companyNameInput.classList.remove("is-invalid");
                    closeButtonCompany.click();
                }
                else if (apiResponse.status === 400) {
                    companyNameInput.classList.add("is-invalid");
                }
            });
        });
    }
    deleteCompanyListener() {
        document.getElementById("buttonDeleteCompanyModal").addEventListener("click", () => {
            let element = document.getElementById("companyOptions");
            let idArray = [];
            let elementArray;
            element.querySelectorAll(`#companyCheckedBoxDelete`).forEach(element => {
                elementArray = element;
                if (elementArray.checked) {
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
                });
            });
            document.getElementById("closeButtonCompanyDelete").click();
            window.location.reload();
        });
    }
}
exports.CompanyService = CompanyService;
