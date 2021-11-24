<template>
    <div>
    <b-sidebar id="sidebar-right" right shadow bg-variant='warning' backdrop backdrop-variant="dark">
      <div class="p-3">
          <div v-if=loggedVerify() class="d-flex align-items-center flex-column">
              <router-link to="Profile">
                    <b-avatar variant="dark" text="PS" class="text-decoration-none mb-3" size="5rem"></b-avatar>
              </router-link>
              <h4 id="sidebar-no-header-title">Fulano Beltrano</h4>
              <div class="divider-menu mb-3"></div>
          </div>
          <div v-if=!loggedVerify() class="d-flex align-items-center flex-column">
                   <button class="styleButton" @click="openModelLogin"><b-avatar variant="dark" class="text-decoration-none mb-3" size="5rem"></b-avatar></button> 
              <h4 id="sidebar-no-header-title">Não logado</h4>
              <div class="divider-menu mb-3"></div>
          </div>
         <nav class="d-box">
             <b-nav class="d-flex align-items-center" vertical>
                 <h5><strong>Highlights</strong></h5>
                 <b-nav-item>Best Sellers</b-nav-item>
                 <b-nav-item>Cheaper</b-nav-item>
                 <h5><strong>Categories</strong></h5>
                 <b-nav-item>Adventure</b-nav-item>
                 <b-nav-item>Romantic</b-nav-item>
                 <b-nav-item>Supernatural</b-nav-item>
                 <b-nav-item>Fantasy</b-nav-item>
             </b-nav>
                 <div class="d-flex align-items-end flex-column bd-highlight mb-3" v-if=loggedVerify()>
                    <b-button size="sm" @click="logout" class="mt-auto" block variant="dark">Logout</b-button>
                </div>
         </nav>
         
      </div>
    </b-sidebar>
  </div>
</template>

<script>
export default {
    methods: {
        openModelLogin(){
            this.loggedVerify();
            this.$emit('click')
        },
        loggedVerify() {
            let token = window.localStorage.getItem("token");
            if (token == null || token.length > 0)
                return true;
            return false;
        },
        logout() {
            window.localStorage.setItem("token", "");
            window.localStorage.setItem("isAdmin", "");
            window.localStorage.setItem("username", "");
            window.location.reload();
        }
    },
}
</script>

<style scoped>
.nav-item > .nav-link{
    color: rgb(0, 0, 0)
}

.divider-menu {
    border: 1px solid rgb(0, 0, 0);
    width: 100%;
}

.styleButton {
    background-color: transparent;
    border: none;
}
</style>