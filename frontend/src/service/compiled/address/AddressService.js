"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.AddressService = void 0;
class AddressService {
    addThToVue() {
        fetch('http://localhost:8080/api/addressTh/form-address', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${window.localStorage.getItem("token")}`
            }
        }).then(async (apiResponse) => {
            if (apiResponse.status === 200) {
                let addressDiv = document.getElementById("registerAddressDiv");
                addressDiv.innerHTML = await apiResponse.text();
            }
        });
    }
    // registerButtonListener() {
    //     document.getElementById("registerButtonAddress")
    //     .addEventListener("click", () => {
    //         let closeButtonAddress = document.getElementById("closeButtonAddress") as HTMLInputElement;
    //         let profileApi = new ProfileApi();
    //         let streetInputAddress = document.getElementById("streetInputAddress") as HTMLInputElement;
    //         let numberInputAddress = document.getElementById("numberInputAddress") as HTMLInputElement;
    //         let zipInputAddress = document.getElementById("zipInputAddress") as HTMLInputElement;
    //         let cityInputAddress = document.getElementById("cityInputAddress") as HTMLInputElement;
    //         let districtInputAddress = document.getElementById("districtInputAddress") as HTMLInputElement;
    //         fetch('http://localhost:8080/api/address', {
    //             method: 'POST',
    //             headers: {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json',
    //             'Authorization': `Bearer ${window.localStorage.getItem("token")}`
    //             },
    //             body: JSON.stringify({
    //                 street: streetInputAddress.value,
    //                 number: numberInputAddress.value,
    //                 zipCode: zipInputAddress.value,
    //                 city: cityInputAddress.value,
    //                 district: districtInputAddress.value
    //             })
    //         }).then(apiResponse => {
    //             if(apiResponse.status === 201) {
    //                 streetInputAddress.classList.remove("is-invalid");
    //                 numberInputAddress.classList.remove("is-invalid");
    //                 zipInputAddress.classList.remove("is-invalid");
    //                 cityInputAddress.classList.remove("is-invalid");
    //                 districtInputAddress.classList.remove("is-invalid");
    //                 profileApi.addAddressOnVue();
    //                 closeButtonAddress.click();
    //                 streetInputAddress.value = "";
    //                 numberInputAddress.value = "";
    //                 zipInputAddress.value = "";
    //                 cityInputAddress.value = "";
    //                 districtInputAddress.value  = "";
    //             } else if(apiResponse.status === 400) {
    //                 streetInputAddress.classList.add("is-invalid");
    //                 numberInputAddress.classList.add("is-invalid");
    //                 zipInputAddress.classList.add("is-invalid");
    //                 cityInputAddress.classList.add("is-invalid");
    //                 districtInputAddress.classList.add("is-invalid");
    //             }
    //         });
    //     });
    // }
    getAddressInfo() {
        return fetch('http://localhost:8080/api/address/find-all', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${window.localStorage.getItem("token")}`
            }
        }).then(apiResponse => {
            if (apiResponse.status === 200) {
                return apiResponse;
            }
            else if (apiResponse.status === 404) {
                return null;
            }
        });
    }
}
exports.AddressService = AddressService;
