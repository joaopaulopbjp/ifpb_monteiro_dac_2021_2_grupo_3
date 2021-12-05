"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.AddressService = void 0;
class AddressService {
    registerButtonListener() {
        document.getElementById("registerButtonAddress")
            .addEventListener("click", () => {
            let closeButtonAddress = document.getElementById("closeButtonAddress");
            let streetInputAddress = document.getElementById("streetInputAddress");
            let numberInputAddress = document.getElementById("numberInputAddress");
            let zipInputAddress = document.getElementById("zipInputAddress");
            let cityInputAddress = document.getElementById("cityInputAddress");
            let districtInputAddress = document.getElementById("districtInputAddress");
            fetch('http://localhost:8080/api/address', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                },
                body: JSON.stringify({
                    street: streetInputAddress.value,
                    number: numberInputAddress.value,
                    zipCode: zipInputAddress.value,
                    city: cityInputAddress.value,
                    district: districtInputAddress.value
                })
            }).then(apiResponse => {
                if (apiResponse.status === 201) {
                    streetInputAddress.classList.remove("is-invalid");
                    numberInputAddress.classList.remove("is-invalid");
                    zipInputAddress.classList.remove("is-invalid");
                    cityInputAddress.classList.remove("is-invalid");
                    districtInputAddress.classList.remove("is-invalid");
                    closeButtonAddress.click();
                }
                else if (apiResponse.status === 400) {
                    streetInputAddress.classList.add("is-invalid");
                    numberInputAddress.classList.add("is-invalid");
                    zipInputAddress.classList.add("is-invalid");
                    cityInputAddress.classList.add("is-invalid");
                    districtInputAddress.classList.add("is-invalid");
                }
            });
        });
    }
}
exports.AddressService = AddressService;
