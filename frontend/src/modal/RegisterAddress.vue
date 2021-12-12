<template>
  <div id="registerAddressDiv" class="boxextern">
  </div>
</template>

<script>
  import { AddressService } from '../service/compiled/address/AddressService';
  let addressService = new AddressService();

  export default {
    data() {
      return {
          CepModel:"#####-###"
      }
    },
    methods: {
          closeModelLogin(){
              this.$emit('click');
          },
          closeModalRegisAddres(){
            this.$emit('click');
          },
      },
      mounted() {
        addressService.addThToVue();
        // addressService.registerButtonListener();
      }
  };
  
</script>

<style>

.font-red {
  color: red;
}

.form-input {
  color: white;
  background: #9539D5;
  border: none;
}
.form-input::placeholder {
  color: white;
}
.form-input:focus {
  color: white;
  background: #9539D5;
  outline: none;
}

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
  background: linear-gradient(138.08deg, #531A7B -13.53%, rgba(110, 38, 161, 0.66) 54.75%, rgba(149, 57, 213, 0.46) 111.81%, rgba(161, 60, 232, 0) 165.13%), #FFFFFF;;
  width: 40%;
  min-width: 300px;
  max-height: 500px;
  padding: 20px;
  border-radius: 8px;
  position: relative;
}

.closed {
  background: transparent;
  position: absolute;
  top: 0px;
  right: 0px;
  width: 50px;
  height: 50px;
  border: none;
  cursor: pointer;
  font-size: 1.4em;
}

.divider-menu {
    border: 1px solid rgb(255, 255, 255);
    width: 100%;
}

</style>