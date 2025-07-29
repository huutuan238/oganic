<script setup>
import 'vue3-carousel/carousel.css'
import { Carousel, Slide, Navigation } from 'vue3-carousel'
import { onMounted } from 'vue';
import axios from 'axios';
let categories = [];
onMounted(async() => {
    await axios
        .get('http://localhost:8080/api/categories')
        .then(response => (categories = response.data))
})

const config = {
    height: 270,
    itemsToShow: 4,
    gap: 5,
    wrapAround: true,
    autoplay: 4000,
    pauseAutoplayOnHover: true,
}
</script>

<template>
    <Carousel v-bind="config">
        <Slide v-for="category in categories" :key="category.id">
            <img :src="category.image" alt="image" />
        </Slide>
        <template #addons>
            <Navigation />
        </template>
    </Carousel>
</template>