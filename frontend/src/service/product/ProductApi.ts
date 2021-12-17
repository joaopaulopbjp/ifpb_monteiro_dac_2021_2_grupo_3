import { Base64 } from '../utils/Base64';
class ProductApi {

    private generateStarButton(starNumber : Number) {
        let html = `<fieldset class="rating mb-4">`;
        for (let index = 1; index <= 5; index++) {
            if(index <= starNumber) {
                html += `<span class="fa fa-star checked"></span>`
            } else {
                html += `<span class="fa fa-star"></span>`
            }
        }
        html += `</fieldset>`
        return html;
    }

    private generateStarAverage(array) {
        let number = 0;
        array.forEach(element => {
            number += element["starNumber"];
        });
        return (number / array.length);
    }

    private generateAuthorList(array) {
        let text = "";
        for (let index = 0; index < array.length-1; index++) {
            text += `${array[index]["name"]}, `
        }
        text += array[array.length-1]["name"];
        return text;
    }

    addProductInfoOnVue() {
        fetch('http://localhost:8080/api/book/find/find-by-id', {
            method: 'POST',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${window.localStorage.getItem("token")}`
            },
            body: JSON.stringify({
                id: window.localStorage.getItem("productId")
            })
        }).then(async (apiResponse) => {
            let json = await apiResponse.json();

            document.getElementById("titleInfoProduct").innerHTML = `
            <h3 class="row" style="color: #FCB13A;">${json["title"]}</h3>
            <h5 class="row" style="color: #FCB13A;">${this.generateAuthorList(json["authorList"])}</h5>
            `;

            document.getElementById("imagemMediaProduct").innerHTML = `
            <img src="${json["imageList"][0]["base64"]}" height="300vh" width="250vw" class="row mt-4" style="margin-left: auto;margin-right: auto;">
            ${this.generateStarButton(this.generateStarAverage(json["evaluateList"]))}
            `;

            document.getElementById("priceProduct").innerHTML = `
            <h5 style="padding: auto;margin: auto;">R$ ${json["price"]}</h5>
            `;

            document.getElementById("credicardProduct").innerHTML = `
            <i class="far fa-credit-card fa-2x" style="color: rebeccapurple;"/><span style="color: gray;font-size: large;">Credicard: R$ ${json["price"]}</span>
            `;

            document.getElementById("descriptionProduct").innerHTML = `
            <span><h5>Description</h5></span>
            <p>${json["description"]}</p>
            `

            await json["evaluateList"].forEach(element => {
                fetch('http://localhost:8080/api/evaluate/find-image-by-id', {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                    },
                    body: JSON.stringify({
                        id: element["id"]
                    })
                }).then(async (apiResponse) => {
                    if(apiResponse.ok) {
                        let evaluate = document.getElementById("evaluateProduct").innerHTML;

                        let imageJson = await apiResponse.json();
                        evaluate += `
                        <div class="row m-3 mt-4 p-3" style="border: 1px solid #D3D3D3; border-radius: 1vw">
                            <img src="${imageJson["response"]}" width="100vw" height="100vw" style="border-radius: 50%"/>
                            
                            <div class="col justify-content-start pl-5">
                                ${this.generateStarButton(element["starNumber"])}
                                <span class="row">${element["comment"]}</span>
                            </div>
                        </div>
                        `
                        document.getElementById("evaluateProduct").innerHTML = evaluate;
                    }
                });
            });

            document.getElementById("addToCartProduct")
            .addEventListener("click", () => {
                fetch('http://localhost:8080/api/shopping-cart/add', {
                      method: 'POST',
                      headers: {
                      'Accept': 'application/json',
                      'Content-Type': 'application/json',
                      "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                      },
                      body: JSON.stringify({
                        itemList: [{
                          amount: 1,
                          idProduct: window.localStorage.getItem("productId")
                        }]
                      })
                  });
            })
        });
    }

    saveEvent() {
        let savebutton = document.getElementById("saveButton");

        let titleInput = document.getElementById("titleInput") as HTMLInputElement;
        let yearLaunchInput = document.getElementById("yearLaunchInput") as HTMLInputElement;
        let pageInput = document.getElementById("pageInput") as HTMLInputElement;
        let priceInput = document.getElementById("priceInput") as HTMLInputElement;
        let inventoryInput = document.getElementById("inventoryInput") as HTMLInputElement;
        let companyInput = document.getElementById("companyInput") as HTMLInputElement;
        let imageInput = document.getElementById("imageInput") as HTMLInputElement;
        let descriptionInput = document.getElementById("descriptionInput") as HTMLInputElement;
        
        let categoryInput = document.getElementById("categoryBox") as HTMLInputElement;
        let authorInput = document.getElementById("authorBox") as HTMLInputElement;

        savebutton.addEventListener("click", async () => {
            let base64 = new Base64();

            let idAuthorArray = this.getIdListFromCheckboxId("authorCheckBox");
            let idCategoryArray = this.getIdListFromCheckboxId("categoriesCheckBox");

            let flag = true;
            
            if(titleInput.value === "") {
                titleInput.classList.add("is-invalid");
                flag = false;
            }else {
                titleInput.classList.remove("is-invalid");
                flag = true;
            }
            if(yearLaunchInput.value === "") {
                yearLaunchInput.classList.add("is-invalid");
                flag = false;
            }else {
                yearLaunchInput.classList.remove("is-invalid");
                flag = true;
            }
            if(pageInput.value === "") {
                pageInput.classList.add("is-invalid");
                flag = false;
            }else {
                pageInput.classList.remove("is-invalid");
                flag = true;
            }
            if(priceInput.value === "") {
                priceInput.classList.add("is-invalid");
                flag = false;
            }else {
                priceInput.classList.remove("is-invalid");
                flag = true;
            }
            if(inventoryInput.value === "") {
                inventoryInput.classList.add("is-invalid");
                flag = false;
            }else {
                inventoryInput.classList.remove("is-invalid");
                flag = true;
            }
            if(companyInput.value === "") {
                companyInput.style.border = "1px solid red";
                flag = false;
            }else {
                companyInput.style.border = "1px solid rgba(146, 146, 146, 0.507)";
                flag = true;
            }
            if(descriptionInput.value === "") {
                descriptionInput.classList.add("is-invalid");
                flag = false;
            }else {
                descriptionInput.classList.remove("is-invalid");
                flag = true;
            }
            if(idAuthorArray.length === 0) {
                authorInput.style.border = "1px solid red";
                flag = false;
            }else {
                authorInput.style.border = "1px solid rgba(146, 146, 146, 0.507)";
                flag = true;
            }
            if(idCategoryArray.length === 0) {
                categoryInput.style.border = "1px solid red";
                flag = false;
            }else {
                categoryInput.style.border = "1px solid rgba(146, 146, 146, 0.507)";
                flag = true;
            }
            if(imageInput.files[0] === undefined) {
                imageInput.classList.add("is-invalid");
                flag = false;
            }else {
                imageInput.classList.remove("is-invalid");
                flag = true;
            }

            if(flag) {
                base64.urlToBase64(imageInput.files[0]).then(async function(image) {
                    fetch('http://localhost:8080/api/book', {
                        method: 'POST',
                        headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        "Authorization": `Bearer ${window.localStorage.getItem("token")}`
                        },
                        body: JSON.stringify({
                            title: `${titleInput.value}`,
                            description: `${descriptionInput.value}`,
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

            if(idArray.length > 0) {
                element.style.border = "1px solid rgba(146, 146, 146, 0.507)";
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

            } else {
                element.style.border = "1px solid red";
            }

        })
    }

    updateProductListener() {
        document.getElementById("buttonUpdateProductView").addEventListener("click", () => {
            let element = document.getElementById("selectProductOptions") as HTMLInputElement;
            let  newTitle = null;
            let  newYearLaunch = null;
            let  newPage = null;
            let  newPrice = null;
           let  newInventory = null;
          // let  newImage = null;
           let  newDescription = null;
            
            if((document.getElementById("newTitleInput") as HTMLInputElement).value != ""){
                newTitle = (document.getElementById("newTitleInput") as HTMLInputElement).value;
            }
            if ((document.getElementById("newYearLaunchInput") as HTMLInputElement).value != "") {
                newYearLaunch = (document.getElementById("newYearLaunchInput") as HTMLInputElement).value;
            }
            if((document.getElementById("newPageInput") as HTMLInputElement).value != ""){
                newPage = (document.getElementById("newPageInput") as HTMLInputElement).value;
            }
            if((document.getElementById("newPriceInput")as HTMLInputElement).value != ""){
                newPrice = (document.getElementById("newPriceInput") as HTMLInputElement).value;
            }
            if((document.getElementById("newInventoryInput")as HTMLInputElement).value != ""){
                newInventory = (document.getElementById("newInventoryInput") as HTMLInputElement).value;
            }
            if((document.getElementById("newDescriptionInput")as HTMLInputElement).value != ""){
                newDescription = (document.getElementById("newDescriptionInput") as HTMLInputElement).value;
            }

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
                    yearLaunch: `${newYearLaunch}`,
                    pages: `${newPage}`,
                    price: `${newPrice}`,
                    inventory: {
                        amount: `${newInventory}`
                    },
                    description: `${newDescription}`,
                    
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