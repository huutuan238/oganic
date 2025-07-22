<template>
  <!-- Hero Section Begin -->
  <section class="hero">
    <div class="container">
      <div class="row">
        <div class="col-lg-3">
          <div class="hero__categories">
            <div class="hero__categories__all" @click.prevent="props.isShow = !props.isShow">
              <i class="fa fa-bars"></i>
              <span>All departments</span>
            </div>
            <ul v-for="category in categories" v-show="props.isShow">
              <li :id="category.id"><router-link :to="`/products/search?categoryId=${category.id}`">{{ category.name
                  }}</router-link></li>
            </ul>
          </div>
        </div>
        <div class="col-lg-9">
          <div class="hero__search">
            <div class="hero__search__form">
              <form :action="`/products/search`">
                <div class="hero__search__categories">
                  All Categories
                  <span class="arrow_carrot-down"></span>
                </div>
                <input type="text" name="q" :value="this.$route.query.q" placeholder="What do you need?">
                <button type="submit" class="site-btn">SEARCH</button>
              </form>
            </div>
            <div class="hero__search__phone">
              <div class="hero__search__phone__icon">
                <i class="fa fa-phone"></i>
              </div>
              <div class="hero__search__phone__text">
                <h5>+65 11.188.888</h5>
                <span>support 24/7 time</span>
              </div>
            </div>
          </div>
          <div class="hero__item set-bg" data-setbg="img/hero/banner.jpg" v-show="isShow">
            <div class="hero__text">
              <span>FRUIT FRESH</span>
              <h2>Vegetable <br />100% Organic</h2>
              <p>Free Pickup and Delivery Available</p>
              <a href="#" class="primary-btn">SHOP NOW</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- Hero Section End -->
</template>

<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';

const props = defineProps({
  isShow: {
    type: Boolean,
    required: false,
    default: false
  }
})

const categories = ref([]);
onMounted(async () => {
  await axios
    .get('http://localhost:8080/api/categories')
    .then(response => (categories.value = response.data))
})
</script>