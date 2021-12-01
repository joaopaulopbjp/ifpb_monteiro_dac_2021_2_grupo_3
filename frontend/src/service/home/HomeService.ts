class HomeService {

    renderbook(pageNumber) {
        let bookDiv = document.getElementById("books-div");
        let html = "";

        this.getBooks(pageNumber).then(async function(response) {
          let json = await response.json();
          json.forEach(element => {
            html += `<div class="text-center border border-warning mb-3 p-2" style="background: white; width: 23%; border-radius: 5px" value="${element["id"]}" >
            <img style="height: 15vw;" src="${element["imageList"]["base64"]}" alt="">
            <h6 class="mt-2">${element["title"]}</h6>
            <b-form-rating class="justify-content-center" id="media" color="#FCB13A" value="4" style="border: none; height: 5vh"/>
            <div class="d-flex justify-content-center" style="height: 8vh;">
              <b-button class="btn-sm" variant="dark" style="border-radius: 10px 0px 0px 10px; background-color: #955DBC;">R$: ${element["price"]}</b-button>
              <b-button class="btn-sm " style="border-radius: 0px 10px 10px 0px; background-color: #FCB13A;">ADD TO CART</b-button>
            </div>
          </div>`
          });
          bookDiv.innerHTML = html;
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
        return await fetch(`http://localhost:8080/api/book/find/find-all/${pageNumber}`, {
          method: 'GET'
        })
        .then(async function(response) {
          return response;
        });
    }
}
export { HomeService };