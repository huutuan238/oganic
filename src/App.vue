<script setup>
import { onMounted } from 'vue';
import FooterPage from './components/FooterPage.vue';
import HeroPage from './components/HeroPage.vue';
import PageHeader from './components/PageHeader.vue';
import { cartStore } from './store/cart';

const cart = cartStore();

onMounted(() => {
  cart.fetchCart();
})
</script>

<template>
  <PageHeader></PageHeader>
  <HeroPage :isShow="false"></HeroPage>

  <main>
    <router-view></router-view>
  </main>
  <FooterPage></FooterPage>
</template>

