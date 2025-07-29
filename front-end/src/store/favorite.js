import { defineStore } from "pinia";

export const favoriteStore = defineStore('favorite', {
    state: () => ({
        count: 0
    }),
    actions: {
        increment() {
            this.count ++;
        }
    }
})