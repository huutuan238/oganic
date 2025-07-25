import { defineStore } from "pinia";

export const userStore = defineStore('user', {
    state: () => ({
        token: localStorage.getItem('token') || "",
    }),
    actions: {
        setToken(token) {
            this.token = token
        },
        removeToken() {
            this.token = "";
        },
        login(user) {
            localStorage.setItem('token', user.token);
            localStorage.setItem('userId', user.id);
            localStorage.setItem('email', user.email);
            this.setToken(user.token);
        },
        logout() {
            localStorage.removeItem('token');
            localStorage.removeItem('userId');
            localStorage.removeItem('email');
            this.removeToken();
        }
    }
})