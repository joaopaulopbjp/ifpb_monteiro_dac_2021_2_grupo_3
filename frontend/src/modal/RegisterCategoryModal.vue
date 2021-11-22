
<template>
  <div>
    <b-button @click="openModal" pill variant="warning" class="my-2 mb-3" type="submit">
      <i class="fas fa-plus-circle"></i> Register Category
    </b-button>
    <b-modal style="background-color: #955DBC" header-bg-variant="warning" body-bg-variant="warning" footer-bg-variant="warning" centered ref="meu_modal">
        <div class="d-grid text-center mb-4">
            <i class="fas fa-shapes mb-3 fa-4x" ></i>
            <h5>Register Category</h5>
        </div>
        <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group
          label-for="name-input"
          invalid-feedback="Name is required"
          :state="nameState"
        >
          <b-form-input
            class="form-control border border-dark text-center"
            placeholder="Name"
            id="name-input"
            :state="nameState"
            required
          ></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "RegisterCategoryModal",
  methods: {
      openModal(){
          this.$refs.meu_modal.show()
      }
  },
}
</script>

<style scoped>
</style>