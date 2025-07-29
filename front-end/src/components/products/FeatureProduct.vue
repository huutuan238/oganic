<script setup>
import { computed, onMounted, ref } from 'vue';
import ProductItem from './ProductItem.vue';
import { favoriteStore } from '@/store/favorite';
import axios from 'axios';
const favorite = favoriteStore();
const products = ref([])
onMounted(() => {
    axios
        .get('http://localhost:8080/api/products')
        .then(response => (products.value = response.data))
})
const categories = computed(() => {
  const set = new Set();
  set.add('All')
  products.value.forEach(product => {
    if (product.category?.name) {
      set.add(product.category.name)
    }
  })
  return Array.from(set)
})
const selectedCategory = ref('All')
const filteredProducts = computed(() => {
  return selectedCategory.value === 'All'
    ? products.value
    : products.value.filter(p => p.category.name === selectedCategory.value)
})
</script>
<template>
    <!-- Featured Section Begin -->
    <section class="featured spad" id="featured-product">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Featured Product</h2>
                    </div>
                    <div class="featured__controls">
                        <ul>
                            <li data-filter=".oranges" v-for="category in categories" :class="{ 'active': selectedCategory === category }"  @click="selectedCategory = category">{{ category }}</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row featured__filter">
                <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat" v-for="product in filteredProducts">
                    <ProductItem :product="product"></ProductItem>
                </div>
            </div>
        </div>
    </section>
    <!-- Featured Section End -->
</template>