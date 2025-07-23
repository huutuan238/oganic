<template>
    <Carousel :items-to-show="1" :wrap-around="true" :autoplay="5000" :pause-autoplay-on-hover="true">
        <template v-for="(group, index) in productGroups" :key="index">
            <Slide>
                <div class="latest-prdouct__slider__item">
                    <router-link v-for="(product, idx) in group" :key="idx" class="latest-product__item" :to="`./product/${product.id}`">
                        <div class="latest-product__item__pic">
                            <img :src="product.image" alt="" />
                        </div>
                        <div class="latest-product__item__text">
                            <h6>
                                {{ product.name }}
                            </h6>
                            <span>{{ product.price }}</span>
                        </div>
                    </router-link>
                </div>
            </Slide>
        </template>
    </Carousel>
</template>

<script setup>
import { computed } from 'vue'
import { Carousel, Slide } from 'vue3-carousel'

// Accept props from parent
const props = defineProps({
    products: {
        type: Array,
        required: true,
        default: () => []
    }
})

// Group products into sets of 3 per slide
const groupSize = 3
const productGroups = computed(() => {
    const groups = []
    for (let i = 0; i < props.products.length; i += groupSize) {
        groups.push(props.products.slice(i, i + groupSize))
    }
    return groups
})
</script>
