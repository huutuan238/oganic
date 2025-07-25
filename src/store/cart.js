import axios from "axios";
import { defineStore } from "pinia";


export const cartStore = defineStore('cart', {
    state: () => ({
        count: 0,
        total: 0
    }),
    actions: {
        addCart(productId, quatity) {
            const token = localStorage.getItem('token');

            const data = {
                userId: Number(localStorage.getItem('userId')),
                productId: productId,
                quatity: quatity || 1,
            }
            axios.post('http://localhost:8080/api/carts/add', JSON.stringify(data), {
                headers: {
                    'Authorization': `Bearer ${token}`,
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
            const token = localStorage.getItem('token');
            if (token) {
                const res = await axios.get('http://localhost:8080/api/carts', {
                    headers: {
                        Authorization: `Bearer ${token}`
                        
                    }
                })
                this.count = res.data.length;
                this.total = res.data.reduce((sum, cart) => {
                    return sum + Number(cart.product.price) * Number(cart.quatity);
                }, 0);
            }

        },
        reset(){
            this.total = 0;
            this.count = 0;
        }
    }
})