class Base64 {
    async urlToBase64(url) {
        let imageBase64 = await new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(url);
            reader.onload = () => resolve(reader.result as string);
            reader.onerror = reject;
        });
        return imageBase64;
    }
}
export { Base64 };