<template>
  <header class="header">
    <img src="@/image/logoBookStore.png" alt="Logo" />
    <input class="field" type="text" placeholder=" Search" />
    <button class="btn search">&#x1F50D;</button>
    <button class="btn car">&#x1F6D2;</button>
    <button class="btn menu"></button>
  </header>
</template>

<script>
export default {};
</script>

<style scoped>
.header {
  background-color: #955abe;
  padding: 5px 10rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
.title {
  font-size: 1.5rem;
  color: #fff;
}
img {
  height: 70px;
}

input::placeholder {
  color: #fff;
}

.btn {
  height: 48px;
  width: 60px;
  border-radius: 8px;
  font-size: 30px;
}

.field {
  justify-content: center;
  height: 35px;
  width: 450px;
  margin-left: 100px;
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
  margin-left: 20%;
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