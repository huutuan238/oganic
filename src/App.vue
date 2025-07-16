<script setup>
import FooterPage from './components/FooterPage.vue';
import HeroPage from './components/HeroPage.vue';
import PageHeader from './components/PageHeader.vue';
</script>

<template>
  <PageHeader></PageHeader>
  <HeroPage></HeroPage>

  <main>
    <router-view></router-view>
  </main>
  <FooterPage></FooterPage>
</template>
