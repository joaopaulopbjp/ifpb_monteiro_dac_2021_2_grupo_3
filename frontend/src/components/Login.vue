<template>
  <div id="" class="boxextern">
    <div class=" boxintern text-center p-5">
      <button class="closed" @click="closeModelLogin">x</button>
      <i class="far fa-user-circle fa-4x text-white"></i>
      <h4 class="text-white">Sign-in</h4>
      <b-form-input class="mt-4" v-model="text" placeholder="username"></b-form-input>
      <b-form @submit.stop.prevent>
          <b-form-input class="mt-3" type="password" id="text-password" placeholder="password" aria-describedby="password-help-block"></b-form-input>
      </b-form>
      <b-button class="mt-3" block variant="outline-warning">Login</b-button>
      <div class="d-flex justify-content-between text-light mt-2">
        <p><input type="checkbox" name="remember" id=""> Remember me</p>
        <router-link class="text-decoration-none text-white" to="" >
          <p>Forget password?</p>
        </router-link>
      </div>
      <div class="d-flex divider-menu mb-3"></div>
      <div class="d-flex justify-content-center text-light mt-2">
          <p>Not a member?</p>
          <router-link class="text-decoration-none text-warning ml-2" to="" >
            <p style="font-size: 1.1em">create account</p>
          </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
        closeModelLogin(){
            this.$emit('click')
        }
    },
};
</script>

<style scoped>

.boxextern {
  width: 100vw;
  height: 100vh; 
  background: rgba(0, 0, 0,.5);
  position: fixed;
  top: 0px;
  left: 0px;
  z-index: 2000;
  display: flex;
  justify-content: center;
  align-items: center;
}

.boxintern {
  background: #955DBC;
  width: 40%;
  min-width: 300px;
  max-height: 500px;
  padding: 20px;
  border: 1px solid black;
  border-radius: 8px;
  position: relative;
}

.closed {
  background: #955DBC;
  position: absolute;
  top: 0px;
  right: 0px;
  width: 50px;
  height: 50px;
  border:  solid #955DBC;
  cursor: pointer;
  font-size: 1.4em;
}

.divider-menu {
    border: 1px solid rgb(255, 255, 255);
    width: 100%;
}

</style>