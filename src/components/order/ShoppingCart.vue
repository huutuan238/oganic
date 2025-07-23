<template>
    <Breadcrumb :title="`Shopping Cart`"></Breadcrumb>
    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th class="shoping__product">Products</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="order in orders">
                                    <td class="shoping__cart__item">
                                        <img :src="order.product.image" alt="">
                                        <h5>{{ order.product.name }}</h5>
                                    </td>
                                    <td class="shoping__cart__price">
                                        ${{ order.product.price }}
                                    </td>
                                    <td class="shoping__cart__quantity">
                                        <div class="quantity">
                                            <div class="pro-qty">
                                                <span class="dec qtybtn" @click="decreaseQuantity(order.id)">-</span>
                                                <input type="number" v-model.number="order.quatity" min="1">
                                                <span class="inc qtybtn" @click="increaseQuantity(order.id)">+</span>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="shoping__cart__total">
                                        ${{ order.quatity * order.product.price }}
                                    </td>
                                    <td class="shoping__cart__item__close">
                                        <span class="icon_close" @click="removeCart(order.id)"></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <router-link :to="'./'" class="primary-btn cart-btn">CONTINUE SHOPPING</router-link>
                        <a href="#" class="primary-btn cart-btn cart-btn-right"><span class="icon_loading"></span>
                            Upadate Cart</a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="shoping__continue">
                        <div class="shoping__discount">
                            <h5>Discount Codes</h5>
                            <form action="#">
                                <input type="text" placeholder="Enter your coupon code">
                                <button type="submit" class="site-btn">APPLY COUPON</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="shoping__checkout">
                        <h5>Cart Total</h5>
                        <ul>
                            <li>Subtotal <span>${{ total }}</span></li>
                            <li>Total <span>${{ total }}</span></li>
                        </ul>
                        <router-link :to="'/checkout'" class="primary-btn">PROCEED TO CHECKOUT</router-link>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shoping Cart Section End -->
</template>

<script setup>
import axios from 'axios';
import Breadcrumb from '../Breadcrumb.vue';
import { computed, onMounted, ref } from 'vue';
let orders = ref([])
const token = localStorage.getItem('token');

onMounted(async () => {
    await axios
        .get('http://localhost:8080/api/carts', {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        .then(response => (orders.value = response.data))
})

function removeCart(orderId) {
    orders.value = orders.value.find(o => o.id !== orderId);
    axios.delete(`http://localhost:8080/api/orders/remove-cart/${orderId}`)
        .then(res => console("remove cart"))
        .catch(error => console.log("remove cart error"))
}

function increaseQuantity(orderId) {
    const order = orders.value.find(o => o.id === orderId);
    order.quatity++;
}

function decreaseQuantity(orderId) {
    const order = orders.value.find(o => o.id === orderId);
    order.quatity--;
}

const total = computed(() => {
    return orders.value.reduce((sum, order) => {
        return sum + Number(order.product.price) * Number(order.quatity);
    }, 0);
});

</script>