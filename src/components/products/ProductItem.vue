<script setup>
defineProps(['product'])
import { favoriteStore } from '@/store/favorite';
const favorite = favoriteStore();
</script>
<template>
    <div class="product__item">
        <div class="product__item__pic set-bg" data-setbg="img/product/product-1.jpg"
            :style="`background-image: url(${product.image})`">
            <ul class="product__item__pic__hover">
                <li><a @click="favorite.increment"><i class="fa fa-heart"></i></a></li>
                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
            </ul>
        </div>
        <div class="product__item__text">
            <h6>
                <router-link :to="`/product/${product.id}`">{{ product.name }}</router-link>
            </h6>
            <h5>{{ product.price }}</h5>
        </div>
    </div>
</template>
