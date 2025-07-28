import axios from "axios";
import { defineStore } from "pinia";
import {useToast} from 'vue-toast-notification';
const $toast = useToast();


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
                this.count++;
                $toast.success("Add to cart success")
            })
                .catch(error =>
                    $toast.error(error.data)
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
        },
        updateTotal(total) {
            this.total = total;
        }
    }
})