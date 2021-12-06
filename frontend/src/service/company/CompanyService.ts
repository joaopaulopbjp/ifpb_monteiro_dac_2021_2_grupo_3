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
}
export { CompanyService };