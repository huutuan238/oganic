<script setup>
import FooterPage from './components/FooterPage.vue';
import HeroPage from './components/HeroPage.vue';
import PageHeader from './components/PageHeader.vue';
</script>

<template>
  <PageHeader :countCartItem="countCartItem"></PageHeader>
  <HeroPage :isShow="false"></HeroPage>

  <main>
    <router-view></router-view>
  </main>
  <FooterPage></FooterPage>
</template>

<script>
export default {
  data() {
    return {
      countCartItem: 10
    }
  }
}
</script>
