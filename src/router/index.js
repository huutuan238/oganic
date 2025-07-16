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

const routes = [
  { path: '/', component: Home },
  { path: '/shop-grid', component: ListProduct },
  { path: '/shop-details', component: ProductDetail },
  { path: '/shoping-cart', component: ShoppingCart },
  { path: '/checkout', component: Checkout },
  { path: '/contact', component: Contact },
  { path: '/blog', component: Blog },
  { path: '/blog-details', component: BlogDetail },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
