class HomeService {

    renderbook(pageNumber) {
        let bookDiv = document.getElementById("books-div");

        this.getBooks(pageNumber).then(async function(response) {
          let json = await response.json();
          let html = "";
          json.forEach(element => {
            html += `<div class="text-center border border-warning mb-3 ml-3 p-2" style="background: white; width: 23%; border-radius: 5px" >
              <button style="border: none" id="buttonImage" value="${element["id"]}">
                <img style="height: 40vh; width:12vw;" src="${element["imageList"][0]["base64"]}" alt="">
              </button>
              <h6 class="mt-2">${element["title"]}</h6>
              <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none; height: 5vh"/>
              <div class="d-flex justify-content-center">
                <span class="btn-sm p-2 text-white" variant="dark" style="border-radius: 8px 0px 0px 8px; background-color: #955DBC;">R$: ${element["price"]}</span>
                <button id="addCartButton" value="${element["id"]}" style="border-radius: 0px 8px 8px 0px; background-color: #FCB13A; padding: 2px 2px">ADD TO CART</button>
              </div>
            </div>`;
            });
            bookDiv.innerHTML = html;
            bookDiv.querySelectorAll('[id=buttonImage]').forEach(element => {
              element;
              (element as HTMLButtonElement).addEventListener("click", () => {
                window.localStorage.setItem("productId", element.attributes.getNamedItem("value").value);
                window.location.replace("http://localhost:8081/#/product");
              })
            });
            bookDiv.querySelectorAll('[id=addCartButton]').forEach(element => {
              element;
              (element as HTMLButtonElement).addEventListener("click", () => {
                if(window.localStorage.getItem("isAdmin") !== "true") {
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
                          idProduct: (element as HTMLButtonElement).value
                        }]
                      })
                  });
                }
              });
            });
        })
    }

    async getTotalPages() {
        return await fetch('http://localhost:8080/api/book/find/find-total-page', {
          method: 'GET'
        })
        .then(async function(response) {
          let json = await response.json();
          return json['response']
        });
    }

    async getBooks(pageNumber) {
        return await fetch(`http://localhost:8080/api/book/find/find-all-available/${pageNumber}`, {
          method: 'GET'
        })
        .then(async function(response) {
          return response;
        });
    }
}

export { HomeService };