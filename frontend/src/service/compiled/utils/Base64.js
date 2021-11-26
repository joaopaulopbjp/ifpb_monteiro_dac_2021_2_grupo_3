"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Base64 = void 0;
class Base64 {
    async urlToBase64(url) {
        let imageBase64 = await new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(url);
            reader.onload = () => resolve(reader.result);
            reader.onerror = reject;
        });
        return imageBase64;
    }
}
exports.Base64 = Base64;
