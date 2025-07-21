// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/components/Home.vue'
import ListProduct from '@/components/products/ListProduct.vue'
import Contact from '@/components/contact/Contact.vue'
import Checkout from '@/components/order/Checkout.vue'
import ShoppingCart from '@/components/order/ShoppingCart.vue'
import ProductDetail from '@/components/products/ProductDetail.vue'
import BlogDetail from '@/components/blogs/BlogDetail.vue'
import Blog from '@/components/blogs/Blog.vue'
import SearchProduct from '@/components/products/SearchProduct.vue'
import Register from '@/components/user/Register.vue'
import Login from '@/components/user/Login.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/shop-grid', component: ListProduct },
  { path: '/product/:id', component: ProductDetail,  props: true },
  { path: '/shoping-cart', component: ShoppingCart },
  { path: '/checkout', component: Checkout },
  { path: '/contact', component: Contact },
  { path: '/blog', component: Blog },
  { path: '/blog-details', component: BlogDetail },
  { path: '/products/search', component: SearchProduct },
  { path: '/login', component: Login },
  { path: '/register', component: Register },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
