"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Sha = void 0;
class Sha {
    constructor() {
        this.sha256 = (message) => {
            const encoder = new TextEncoder();
            const data = encoder.encode(message);
            return window.crypto.subtle.digest('SHA-256', data);
        };
    }
}
exports.Sha = Sha;
