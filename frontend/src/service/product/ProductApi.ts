import { Base64 } from '../utils/Base64';
class ProductApi {
    saveEvent() {
        // let base64 = new Base64();
        let savebutton = document.getElementById("saveButton");

        let titleInput = document.getElementById("titleInput") as HTMLInputElement;
        let yearLaunchInput = document.getElementById("yearLaunchInput") as HTMLInputElement;
        let pageInput = document.getElementById("pageInput") as HTMLInputElement;
        let priceInput = document.getElementById("priceInput") as HTMLInputElement;
        let inventoryInput = document.getElementById("inventoryInput") as HTMLInputElement;
        let companyInput = document.getElementById("companyInput") as HTMLInputElement;
        let imageInput = document.getElementById("imageInput") as HTMLInputElement;
        let descriptionInput = document.getElementById("descriptionInput") as HTMLInputElement;        

        savebutton.addEventListener("click", async () => {
            let base64 = new Base64();

            let idAuthorArray = this.getIdListFromCheckboxId("authorCheckBox");
            let idCategoryArray = this.getIdListFromCheckboxId("categoriesCheckBox");
            

            if(imageInput.files[0] !== undefined) {
                base64.urlToBase64(imageInput.files[0]).then(async function(image) {
                    fetch('http://localhost:8080/api/book', {
                        method: 'POST',
                        headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                        },
                        body: JSON.stringify({
                            title: titleInput.value,
                            description: descriptionInput.value,
                            yearLaunch: yearLaunchInput.value,
                            pages: pageInput.value,
                            price: priceInput.value,
                            inventory: {
                                amount: inventoryInput.value
                            },
                            imageList: [{
                                base64: image
                            }],
                            idCategoryList: idCategoryArray,
                            idCompany: companyInput.value,
                            idAuthorList: idAuthorArray
                        })
                    });
                })
            }
        });
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

export { ProductApi };