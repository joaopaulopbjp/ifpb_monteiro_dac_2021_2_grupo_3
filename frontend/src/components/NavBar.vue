<template>
  <header class="header">
    <nav>
      <img src="@/image/logoBookStore.png" alt="Logo" />
      <nav id="box-btn-input">
        <input class="field" type="text" placeholder=" Search" />
        <button class="btn search">&#x1F50D;</button>
      </nav>
      
      <button class="btn car">&#x1F6D2;</button>
      <img @click="menuActive = !menuActive" alt="men" class="btn menu" src="https://raw.githubusercontent.com/william-costa/wdev-mock-site-resources/master/assets/images/menu.svg" >

      <div @click="menuActive = !menuActive" class="menu-overlay" v-if="menuActive"></div>
      <div class="menu-items" v-if="menuActive">
          <ul>
            <div class="box-profile">
                <router-link to="Profile">
                    <img alt="men" class="icon-menu" src="https://cdn-icons-png.flaticon.com/512/64/64572.png">
                    <h3>Profile</h3>
                </router-link>
            </div>
            <h3>Highlights</h3>
            <li><a href="/">Best Sellers</a></li>
            <li><a href="/">Cheaper</a></li>
            <h3>Cartegory</h3>
            <li><a href="/">Adventury</a></li>
            <li><a href="/">Romantic</a></li>
            <li><a href="/">Supernatural</a></li>
            <li><a href="/">Fantasy</a></li>
            <h3>Filter</h3>
            <li><a href="/">Adventury</a></li>
            <li><a href="/">Romantic</a></li>
            <li><a href="/">Supernatural</a></li>
            <li><a href="/">Fantasy</a></li>
          </ul>
      </div>
    
    </nav>
  </header>
</template>

<script>
export default {
  name: 'Header',
  data() {
    return {
      menuActive: false
    }
  }
  
};
</script>

<style scoped>
.header {
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
  height:50px;
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

ul ,.box-profile {
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

ul , li {
  margin: 15px 0; 
}

ul, li, a {
  color: #fff;
}
ul, li, h3 {
  color: black;
}

/* @media only screen and (max-width: 800px) {
  .header {
    padding: 1em 1em;
  }
  img {
    height: 50px;
  }
} */
</style>