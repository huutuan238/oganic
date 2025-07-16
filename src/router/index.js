// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/components/Home.vue'
import FromBlog from '@/components/blogs/FromBlog.vue'
import ListProduct from '@/components/products/ListProduct.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/shop-grid', component: ListProduct },
  { path: '/shop-details', component: Home },
  { path: '/shoping-cart', component: Home },
  { path: '/checkout', component: Home },
  { path: '/contact', component: Home },
  { path: '/blog', component: FromBlog },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
