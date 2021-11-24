"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProfileApi = void 0;
class ProfileApi {
    isAdmin() {
        let isAdmin = window.localStorage.getItem("isAdmin");
        if (isAdmin === 'true') {
            return true;
        }
        return false;
    }
}
exports.ProfileApi = ProfileApi;
