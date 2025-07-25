<script setup>
import { cartStore } from '@/store/cart';
import { favoriteStore } from '@/store/favorite';
import { userStore } from '@/store/user';
import { useRouter } from 'vue-router';
const favorite = favoriteStore();
const cart = cartStore();
const user = userStore();
const router = useRouter()
function logout() {
  user.logout();
  cart.reset();
  router.push('/login')
}
</script>
<template>
  <!-- Header Section Begin -->
  <header class="header">
    <div class="header__top">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 col-md-6">
            <div class="header__top__left">
              <ul>
                <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                <li>Free Shipping for all Order of $99</li>
              </ul>
            </div>
          </div>
          <div class="col-lg-6 col-md-6">
            <div class="header__top__right">
              <div class="header__top__right__social">
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="#"><i class="fa fa-linkedin"></i></a>
                <a href="#"><i class="fa fa-pinterest-p"></i></a>
              </div>
              <div class="header__top__right__language">
                <img src="../assets/img/language.png" alt="">
                <div>English</div>
                <span class="arrow_carrot-down"></span>
                <ul>
                  <li><a href="#">Spanis</a></li>
                  <li><a href="#">English</a></li>
                </ul>
              </div>
              <div class="header__top__right__auth" v-if="user.token.length === 0">
                <router-link :to="'/login'"><i class="fa fa-user"></i>Login</router-link>
              </div>
              <div class="header__top__right__auth" v-else>
                <a @click="logout()"><i class="fa fa-sign-out"></i>Logout</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-lg-3">
          <div class="header__logo">
            <router-link :to="'/'"><img src="../assets/img/logo.png" alt=""></router-link>
          </div>
        </div>
        <div class="col-lg-6">
          <nav class="header__menu">
            <ul>
              <li :class="{ active: $route.path === '/' }">
                <router-link :to="'/'">Home</router-link>
              </li>
              <li :class="{ active: $route.path === '/shop-grid' }"><router-link to="/shop-grid">Shop</router-link></li>
              <li><router-link to="/#">pages</router-link>
                <ul class="header__menu__dropdown">
                  <li><router-link to="/shoping-cart">Shoping Cart</router-link></li>
                  <li><router-link to="/checkout">Check Out</router-link></li>
                </ul>
              </li>
              <li :class="{ active: $route.path === '/blog' }"><router-link to="/blog">Blog</router-link></li>
              <li :class="{ active: $route.path === '/contact' }"><router-link to="/contact">Contact</router-link></li>
            </ul>
          </nav>
        </div>
        <div class="col-lg-3">
          <div class="header__cart">
            <ul>
              <li><a href="#"><i class="fa fa-heart"></i> <span>{{ favorite.count }}</span></a></li>
              <li><router-link :to="`/shoping-cart`"><i class="fa fa-shopping-bag"></i> <span>{{ cart.count
                    }}</span></router-link></li>
            </ul>
            <div class="header__cart__price">item: <span>${{ cart.total }}</span></div>
          </div>
        </div>
      </div>
      <div class="humberger__open">
        <i class="fa fa-bars"></i>
      </div>
    </div>
  </header>
  <!-- Header Section End -->
</template>