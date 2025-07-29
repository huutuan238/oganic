<script setup>
import LatestProductItem from './LatestProductItem.vue';
</script>

<template>
    <!-- Latest Product Section Begin -->
    <section class="latest-product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Latest Products</h4>
                        <LatestProductItem :products="latestProducts"></LatestProductItem>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Top Rated Products</h4>
                        <LatestProductItem :products="reviewProducts"></LatestProductItem>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Review Products</h4>
                        <LatestProductItem :products="reviewProducts"></LatestProductItem>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Latest Product Section End -->
</template>
<script>
import axios from 'axios';

export default {
    data() {
        return {
            latestProducts: [],
            ratedProducts: [],
            reviewProducts: [],
        }
    },
    mounted() {
        axios
            .get(`http://localhost:8080/api/products/latest-products`)
            .then(response => {
                this.latestProducts = response.data;
                // TODO: get rated products
                this.ratedProducts = response.data;
                // TODO: get review products
                this.reviewProducts = response.data;
            })
    },
}

</script>