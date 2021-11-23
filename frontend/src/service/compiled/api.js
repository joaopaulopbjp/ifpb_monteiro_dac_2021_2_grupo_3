"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.api = void 0;
class api {
    async login() {
        const rawResponse = await fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: "admin",
                senha: "admin"
            })
        }).catch(error => {
            alert(error);
        });
    }
}
exports.api = api;
