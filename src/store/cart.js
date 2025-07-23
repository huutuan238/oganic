import axios from "axios";
import { defineStore } from "pinia";

export const cartStore = defineStore('cart', {
    state: () => ({
        count: 0,
        total: 0
    }),
    actions: {
        addCart(productId, quatity) {
            const data = {
                userId: 1,
                productId: productId,
                quatity: quatity || 1,
            }
            axios.post('http://localhost:8080/api/carts/add', JSON.stringify(data), {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => {
                console.log(res.data);
                this.count++;
            })
                .catch(error =>
                    console.log(error)
                )
        },
        async fetchCart() {
            const res = await axios.get('http://localhost:8080/api/carts')
            this.count = res.data.length;
            this.total =  res.data.reduce((sum, cart) => {
                return sum + Number(cart.product.price) * Number(cart.quatity);
            }, 0);
        }
    }
})