<template>
  <div id="" class="boxextern">
    <div class=" boxintern text-center p-5">
      <button id="closeButton" class="closed" @click="closeModelLogin">x</button>
      <svg width="70%"  viewBox="0 0 383 140" fill="none" xmlns="http://www.w3.org/2000/svg">
      <circle cx="189" cy="52" r="51" stroke="white" stroke-width="2"/>
      <g clip-path="url(#clip0_9_11)">
      <path d="M156 84V85H157H222H223V84V76C223 72.9667 221.449 70.3877 219.12 68.2712C216.797 66.1603 213.629 64.4385 210.185 63.0798C203.296 60.3627 195.046 59 189.5 59C183.954 59 175.704 60.3627 168.815 63.0798C165.371 64.4385 162.203 66.1603 159.88 68.2712C157.551 70.3877 156 72.9667 156 76V84ZM189.5 53C199.016 53 206.75 45.4068 206.75 36C206.75 26.5932 199.016 19 189.5 19C179.984 19 172.25 26.5932 172.25 36C172.25 45.4068 179.984 53 189.5 53Z" stroke="white" stroke-width="2"/>
      </g>
      <line x1="253" y1="123" x2="383" y2="123" stroke="white" stroke-width="2"/>
      <line y1="123" x2="130" y2="123" stroke="white" stroke-width="2"/>
      <path d="M152.923 123.493C150.511 122.8 148.753 121.95 147.649 120.944C146.556 119.929 146.009 118.679 146.009 117.194C146.009 115.515 146.678 114.128 148.016 113.034C149.363 111.931 151.111 111.379 153.26 111.379C154.725 111.379 156.028 111.662 157.171 112.229C158.323 112.795 159.212 113.576 159.837 114.572C160.472 115.568 160.789 116.657 160.789 117.839H157.962C157.962 116.55 157.552 115.539 156.731 114.807C155.911 114.064 154.754 113.693 153.26 113.693C151.873 113.693 150.789 114.001 150.008 114.616C149.236 115.222 148.851 116.066 148.851 117.15C148.851 118.02 149.217 118.757 149.949 119.362C150.691 119.958 151.946 120.505 153.714 121.003C155.491 121.501 156.878 122.053 157.874 122.658C158.88 123.254 159.622 123.952 160.101 124.753C160.589 125.554 160.833 126.496 160.833 127.58C160.833 129.309 160.159 130.695 158.812 131.74C157.464 132.775 155.662 133.293 153.406 133.293C151.941 133.293 150.574 133.015 149.305 132.458C148.035 131.892 147.054 131.12 146.36 130.144C145.677 129.167 145.335 128.059 145.335 126.818H148.162C148.162 128.107 148.636 129.128 149.583 129.88C150.54 130.622 151.814 130.993 153.406 130.993C154.891 130.993 156.028 130.69 156.819 130.085C157.61 129.479 158.006 128.654 158.006 127.609C158.006 126.564 157.64 125.759 156.907 125.192C156.175 124.616 154.847 124.05 152.923 123.493ZM166.971 133H164.261V117.15H166.971V133ZM164.041 112.946C164.041 112.507 164.173 112.136 164.437 111.833C164.71 111.53 165.11 111.379 165.638 111.379C166.165 111.379 166.565 111.53 166.839 111.833C167.112 112.136 167.249 112.507 167.249 112.946C167.249 113.386 167.112 113.752 166.839 114.045C166.565 114.338 166.165 114.484 165.638 114.484C165.11 114.484 164.71 114.338 164.437 114.045C164.173 113.752 164.041 113.386 164.041 112.946ZM170.677 124.943C170.677 122.473 171.248 120.51 172.391 119.055C173.533 117.59 175.047 116.857 176.932 116.857C178.865 116.857 180.374 117.541 181.458 118.908L181.59 117.15H184.065V132.619C184.065 134.67 183.455 136.286 182.234 137.468C181.023 138.649 179.393 139.24 177.342 139.24C176.199 139.24 175.081 138.996 173.987 138.508C172.894 138.02 172.059 137.351 171.482 136.501L172.889 134.875C174.051 136.311 175.472 137.028 177.151 137.028C178.47 137.028 179.495 136.657 180.228 135.915C180.97 135.173 181.341 134.128 181.341 132.78V131.418C180.257 132.668 178.777 133.293 176.902 133.293C175.047 133.293 173.543 132.546 172.391 131.052C171.248 129.558 170.677 127.521 170.677 124.943ZM173.401 125.251C173.401 127.038 173.768 128.444 174.5 129.47C175.232 130.485 176.258 130.993 177.576 130.993C179.285 130.993 180.54 130.217 181.341 128.664V121.428C180.511 119.914 179.266 119.157 177.605 119.157C176.287 119.157 175.257 119.67 174.515 120.695C173.772 121.721 173.401 123.239 173.401 125.251ZM190.73 117.15L190.818 119.143C192.029 117.619 193.611 116.857 195.564 116.857C198.914 116.857 200.604 118.747 200.633 122.526V133H197.923V122.512C197.913 121.369 197.649 120.524 197.132 119.978C196.624 119.431 195.828 119.157 194.744 119.157C193.865 119.157 193.094 119.392 192.43 119.86C191.766 120.329 191.248 120.944 190.877 121.706V133H188.167V117.15H190.73ZM215.604 133H212.791V111.672H215.604V133ZM222.898 117.15L222.986 119.143C224.197 117.619 225.779 116.857 227.732 116.857C231.082 116.857 232.771 118.747 232.801 122.526V133H230.091V122.512C230.081 121.369 229.817 120.524 229.3 119.978C228.792 119.431 227.996 119.157 226.912 119.157C226.033 119.157 225.262 119.392 224.598 119.86C223.934 120.329 223.416 120.944 223.045 121.706V133H220.335V117.15H222.898Z" fill="white"/>
      <defs>
      <clipPath id="clip0_9_11">
      <rect width="91" height="77" fill="white" transform="translate(144 7)"/>
      </clipPath>
      </defs>
      </svg>

      <b-form-input id="usernameInput" class="mt-4 form-input" placeholder="Username"></b-form-input>
      <b-form @submit.stop.prevent>
          <b-form-input id="passwordInput" class="mt-3 form-input" type="password" placeholder="Password" aria-describedby="password-help-block" required></b-form-input>
      </b-form>
      <div id="error-mensage" style="display: none;">
        <p class="justify-content-start d-flex font-red" style="font-size: 2vh">Username or password is incorrect</p>
      </div>
      <b-button @click="login" class="mt-3" block variant="outline-warning">Login</b-button>
      <div class="d-flex justify-content-between text-light mt-2">
        <p><input type="checkbox" name="remember" id=""> Remember me</p>
        <router-link class="text-decoration-none text-white" to="" >
          <p>Forget password?</p>
        </router-link>
      </div>
      <div class="d-flex divider-menu mb-3"></div>
      <div class="d-flex justify-content-center text-light mt-2">
          <p>Not a member?</p>
          <router-link class="text-decoration-none text-warning ml-2" to="RegisterUser" >
            <p style="font-size: 1.1em">create account</p>
          </router-link>
      </div>
    </div>
  </div>
</template>

<script>
  import { LoginApi as LoginApiImport } from "../service/compiled/login/LoginApi.js";
  let loginApi = new LoginApiImport();
  export default {
    methods: {
          login() {
            loginApi.login().then(status => {
              if(status) {
                window.location.reload();
              }
            });
          },
          closeModelLogin(){
              this.$emit('click');
          }
      },
  };
</script>

<style scoped>

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