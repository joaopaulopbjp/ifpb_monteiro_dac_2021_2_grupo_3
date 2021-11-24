class ProfileApi {
    isAdmin() {
        let isAdmin = window.localStorage.getItem("isAdmin");

        if(isAdmin === 'true'){
            return true;
        }
        return false;
    }
}
export { ProfileApi };