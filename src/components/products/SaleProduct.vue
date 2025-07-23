<template>
  <Carousel :wrapAround="true" :mouseDrag="true" :autoplay="2000" :items-to-show="3" :width="270">
    <Slide v-for="(product, index) in products" :key="index">
      <div class="product__discount__item">
        <div class="product__discount__item__pic" :style="{ backgroundImage: `url(${product.image})`, width: 270 }">
          <div class="product__discount__percent">{{ product.discount* 100 }}%</div>
          <ul class="product__item__pic__hover">
            <li><a href="#"><i class="fa fa-heart"></i></a></li>
            <li><a href="#"><i class="fa fa-retweet"></i></a></li>
            <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
          </ul>
        </div>
        <div class="product__discount__item__text">
          <span>{{ product.category.name }}</span>
          <h5><a href="#">{{ product.name }}</a></h5>
          <div class="product__item__price">
            ${{ product.price*(1- product.discount) }} <span>${{ product.price }}</span>
          </div>
        </div>
      </div>
    </Slide>
  </Carousel>
</template>

<script setup>
import axios from 'axios'
import { onMounted, ref } from 'vue'
import { Carousel, Slide } from 'vue3-carousel'
const products = ref([])
onMounted(() => {
    axios
        .get('http://localhost:8080/api/products')
        .then(response => (products.value = response.data))
})

</script>
