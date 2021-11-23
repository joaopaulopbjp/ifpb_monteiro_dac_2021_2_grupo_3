<template>
    <div>
    <b-sidebar id="sidebar-right" right shadow bg-variant='warning' backdrop backdrop-variant="dark">
      <div class="p-3">
          <div v-if="logged === true" class="d-flex align-items-center flex-column">
              <router-link to="Profile">
                    <b-avatar variant="dark" text="PS" class="text-decoration-none mb-3" size="5rem"></b-avatar>
              </router-link>
              <h4 id="sidebar-no-header-title">Fulano Beltrano</h4>
              <div class="divider-menu mb-3"></div>
          </div>
          <div v-else-if="logged === false" class="d-flex align-items-center flex-column">
                   <button @click="openModelLogin()"><b-avatar variant="dark" class="text-decoration-none mb-3" size="5rem"></b-avatar></button> 
              <h4 id="sidebar-no-header-title">Não logado</h4>
              <div class="divider-menu mb-3"></div>
          </div>
         <nav>
             <b-nav vertical>
                 <h5><strong>Highlights</strong></h5>
                 <b-nav-item>Best Sellers</b-nav-item>
                 <b-nav-item>Cheaper</b-nav-item>
                 <h5><strong>Cartegorys</strong></h5>
                 <b-nav-item>Adventure</b-nav-item>
                 <b-nav-item>Romantic</b-nav-item>
                 <b-nav-item>Supernatural</b-nav-item>
                 <b-nav-item>Fantasy</b-nav-item>
             </b-nav>
         </nav>
      </div>
    </b-sidebar>
  </div>

</template>

<script>
export default {
    props: ['logged'],
    methods: {
        openModelLogin(){
            this.$emit('click')
            
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
</style>