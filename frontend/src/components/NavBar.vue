<template>
  <div style="background-color: #955DBC">
    <div class="container">
    <b-navbar toggleable="lg" type="dark">
      <b-navbar-brand href="/">
          <img src="@/image/logoBookStore.png" alt="Logo" width=100/>
      </b-navbar-brand>
      <b-navbar-nav class="ml-auto">
          <b-nav-form>
              <b-form-input class="mr-sm-2" placeholder="Search" style="width:400px"></b-form-input>
              <b-button variant="warning" class="my-2 my-sm-0" type="submit">
                  <i class="fas fa-search"></i>
              </b-button>
          </b-nav-form>
          <b-nav-item>
              <b-button class="align-items-left" variant="outline-light" to="ShoppingCart">
                  <i class="fas fa-shopping-cart" style="width: 50"></i>
                  <b-badge class="ml-2 mt-0" variant="danger">{{shoppingCartSize}}</b-badge>
              </b-button>
          </b-nav-item>
          <b-nav-item>
              <b-button variant="outline-light" v-b-toggle.sidebar-right>
                  <i class="fas fa-bars"></i>
              </b-button>
          </b-nav-item>
      </b-navbar-nav>
    </b-navbar>
    </div>
  </div>
 
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      menuActive: false,
      shoppingCartSize: "0"
    };
  },
  mounted() {
    this.getShoppingCartSize()
  },
  methods: {
    async getShoppingCartSize() {
      if(window.localStorage.getItem("token") !== null && window.localStorage.getItem("token") !== "" ) {
        this.shoppingCartSize = await fetch('http://localhost:8080/api/shopping-cart/get-total', {
            method: 'GET',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${window.localStorage.getItem("token")}`
            }
        }).then(async function(response) {
          let json = await response.json();
          return json["response"];
        });
      }
    }
  }
};
</script>

<style scoped>
/* .header {
  width: 100%;
  height: 80px;
  background-color: #955abe;
  display: flex;
  justify-content: center;
  align-items: center;
}

nav {
  justify-content: space-between;
  display: flex;
  width: 80%;
}

#box-btn-input {
  display: inline center;
  justify-content: center;
}

.title {
  font-size: 1.5rem;
  color: #fff;
}
img {
  height: 50px;
}

.icon-menu {
  width: 50px;
}

input::placeholder {
  color: #fff;
}

.btn {
  height: 48px;
  width: 60px;
  border-radius: 8px;
  border-color: #fff;
  font-size: 30px;
}

.field {
  font-size: 20px;
  height: 35px;
  width: 450px;
  padding: 5px 10px;
  border: 1px solid #fff;
  border-radius: 8px;
  background: transparent;
  color: #fff;
}

.search {
  background-color: #fcb13a;
  color: #fff;
}

.car {
  background: transparent;
}

.menu {
  width: 30px;
  margin-left: 25px;
}

.menu-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 80%;
  height: 100vh;
  background: black;
  opacity: 0.6;
}

.menu-items {
  position: fixed;
  top: 0;
  right: 0;
  background-color: #955abe;
  width: 20%;
  height: 100vh;
  display: flex;
  justify-content: center;
}

.box-profile {
  background-color: #fcb13a;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}

ul,
.box-profile {
  width: 100%;
  height: 15vh;
}

a {
  text-decoration: none;
}

ul {
  list-style: none;
  text-align: center;
}

ul,
li {
  margin: 15px 0;
}

ul,
li,
a {
  color: #fff;
}
ul,
li,
h3 {
  color: black;
}
 */
/* @media only screen and (max-width: 800px) {
  .header {
    padding: 1em 1em;
  }
  img {
    height: 50px;
  }
} */
</style>