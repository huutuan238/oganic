<script setup>
import ProductItem from './ProductItem.vue';
import { favoriteStore } from '@/store/favorite';
const favorite = favoriteStore();
</script>
<template>
    <!-- Featured Section Begin -->
    <section class="featured spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Featured Product</h2>
                    </div>
                    <div class="featured__controls">
                        <ul>
                            <li class="active" data-filter="*">All</li>
                            <li data-filter=".oranges">Oranges</li>
                            <li data-filter=".fresh-meat">Fresh Meat</li>
                            <li data-filter=".vegetables">Vegetables</li>
                            <li data-filter=".fastfood">Fastfood</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row featured__filter">
                <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat" v-for="product in products">
                    <ProductItem :product="product"></ProductItem>
                </div>
            </div>
        </div>
    </section>
    <!-- Featured Section End -->
</template>
<script>
import axios from 'axios';
export default {
    data() {
        return {
            products: []
        };
    },
    mounted() {
        axios
            .get('http://localhost:8080/api/products')
            .then(response => (this.products = response.data))
    },
    methods: {
        addCart() {
            console.log("Add cart")
        },
        addFavorite() {
            console.log("Add favorite")
        },

    }
};
</script>