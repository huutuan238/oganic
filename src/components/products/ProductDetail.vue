<script setup>
import { favoriteStore } from '@/store/favorite';
import axios from 'axios';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import Breadcrumb from '../Breadcrumb.vue';
import { cartStore } from '@/store/cart';
import ProductItem from './ProductItem.vue';
const favorite = favoriteStore();
const cart = cartStore();
const product = ref([]);
const quatity = ref(1);
function increaseQuantity() {
    quatity.value++;
}

const route = useRoute()
onMounted(async () => {
    const productId = route.params.id;
    await axios.get(`http://localhost:8080/api/products/${productId}`)
        .then(res => {
            product.value = res.data;
        })
})
</script>

<template>
    <Breadcrumb :title="product.name"></Breadcrumb>
    <!-- Product Details Section Begin -->
    <section class="product-details spad" id="product-details">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__item">
                            <img class="product__details__pic__item--large" :src="product.image" alt="">
                        </div>
                        <div class="product__details__pic__slider owl-carousel">
                            <img data-imgbigurl="img/product/details/product-details-2.jpg"
                                src="../../assets/product/details/thumb-1.jpg" alt="">
                            <img data-imgbigurl="img/product/details/product-details-3.jpg"
                                src="../../assets/product/details/thumb-2.jpg" alt="">
                            <img data-imgbigurl="img/product/details/product-details-5.jpg"
                                src="../../assets/product/details/thumb-3.jpg" alt="">
                            <img data-imgbigurl="img/product/details/product-details-4.jpg"
                                src="../../assets/product/details/thumb-4.jpg" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__text">
                        <h3 class="product-name">{{ product.name }}</h3>
                        <div class="product__details__rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star-half-o"></i>
                            <span>(18 reviews)</span>
                        </div>
                        <div class="product__details__price">{{ product.price }}</div>
                        <p class="product-description">{{ product.description }}</p>
                        <div class="product__details__quantity">
                            <div class="quantity">
                                <div class="pro-qty">
                                    <span class="dec qtybtn">-</span>
                                    <input type="number" :value="quatity" class="quatity-value">
                                    <span class="inc qtybtn" @click="increaseQuantity()">+</span>
                                </div>
                            </div>
                        </div>
                        <a @click="cart.addCart(product.id, quatity)" class="primary-btn">ADD TO CARD</a>
                        <a @click="favorite.increment" class="heart-icon"><span class="icon_heart_alt"></span></a>
                        <ul>
                            <li>
                                <b>Availability</b>
                                <span v-if="product.stock">In Stock</span>
                                <span v-else>Not Available</span>
                            </li>
                            <li><b>Shipping</b> <span>01 day shipping. <samp>Free pickup today</samp></span></li>
                            <li><b>Weight</b> <span>0.5 kg</span></li>
                            <li><b>Share on</b>
                                <div class="share">
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-instagram"></i></a>
                                    <a href="#"><i class="fa fa-pinterest"></i></a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="product__details__tab">
                        <b-tabs>
                            <b-tab title="Description" active>
                                <div class="product__details__tab__desc">
                                    <h6>Description</h6>
                                    <p>{{ product.description }}</p>
                                </div>
                            </b-tab>

                            <b-tab title="Information">
                                <div class="product__details__tab__desc">
                                    <h6>Products Information</h6>
                                    <p>
                                        Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui.
                                        Pellentesque in ipsum id orci porta dapibus. ...
                                    </p>
                                </div>
                            </b-tab>
                            <b-tab title="Reviews (1)">
                                <div class="product__details__tab__desc">
                                    <h6>Products Information</h6>
                                    <p>
                                        Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui.
                                        Pellentesque in ipsum id orci porta dapibus. ...
                                    </p>
                                </div>
                            </b-tab>
                        </b-tabs>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Details Section End -->
    <!-- Related Product Section Begin -->
    <section class="related-product">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title related__product__title">
                        <h2>Related Product</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3 col-md-4 col-sm-6" v-for="relatedProduct in product.relatedProducts">
                    <ProductItem :product="relatedProduct"></ProductItem>
                </div>
            </div>
        </div>
    </section>
    <!-- Related Product Section End -->
</template>
