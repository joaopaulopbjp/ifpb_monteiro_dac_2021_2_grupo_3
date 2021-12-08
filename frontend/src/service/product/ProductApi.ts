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
                    }).then(apiResponse => {
                        if(apiResponse.status === 201){
                            document.getElementById("cancelRegisterProduct").click();
                        }
                    });
                })
            }
        });
    }

    deleteProductListener(){
        document.getElementById("buttonDeleteProductModal").addEventListener("click", () => {
            let element = document.getElementById("ProductOptionsDelete") as HTMLInputElement;
            let idArray : Array<Number> = [];
            let elementArray;
            element.querySelectorAll(`#productCheckedBoxDelete`).forEach(element => {
                elementArray = element as HTMLInputElement;
                if(elementArray.checked) {
                    idArray.push(elementArray.value);
                }
            });

            idArray.forEach(element => {
                fetch('http://localhost:8080/api/book', {
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

    updateProductListener() {
        document.getElementById("buttonUpdateProductView").addEventListener("click", () => {
            let element = document.getElementById("selectProductOptions") as HTMLInputElement;
            let  newTitle = null;
            
            if((document.getElementById("newTitleInput") as HTMLInputElement).value != ""){
                newTitle = (document.getElementById("newTitleInput") as HTMLInputElement).value;
            }
      /*       let  newYearLaunch = null;
            let  newPage = null;
            let  newPrice = null;
            let  newInventory = null;
            let  newImage = null;
            let  newDescription = null;

            if((document.getElementById("newYearLaunchInput") as HTMLInputElement).value != ""){
                newYearLaunch = (document.getElementById("newYearLaunchInput") as HTMLInputElement).value;
            }
            if((document.getElementById("newPageInput") as HTMLInputElement).value != ""){
                newPage = (document.getElementById("newPageInput") as HTMLInputElement).value;
            }
            if((document.getElementById("newPriceInput") as HTMLInputElement).value != ""){
                newPrice = (document.getElementById("newPriceInput") as HTMLInputElement).value;
            }
            if((document.getElementById("newInventoryInput") as HTMLInputElement).value != ""){
                newInventory = (document.getElementById("newInventoryInput") as HTMLInputElement).value;
            }
            if((document.getElementById("newimageInput") as HTMLInputElement).value != ""){
                newImage = (document.getElementById("newimageInput") as HTMLInputElement).value;
            }
            if((document.getElementById("newDescriptionInput") as HTMLInputElement).value != ""){
                newDescription = (document.getElementById("newDescriptionInput") as HTMLInputElement).value;
            } */

            
            fetch('http://localhost:8080/api/book', {
                method: 'PUT',
                headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${window.localStorage.getItem("token")}`
                },
                body: JSON.stringify({
                    id: `${element.value}`,
                    title: `${newTitle}`,
                    /* yearLaunch: `${newYearLaunch}`,
                    pages: `${newPage}`,
                    price: `${newPrice}`,
                    inventory: `${newInventory}`,
                    description: `${newDescription}`,
                    image: `${newImage}`, */
                })
            })
            window.location.assign ("/#/Profile");
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

export { ProductApi };