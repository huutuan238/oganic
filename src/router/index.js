// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/components/Home.vue'
import FromBlog from '@/components/blogs/FromBlog.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/blog', component: FromBlog },
]

const router = createRouter({
  history: createWebHistory(), // ✅ for browser routing
  routes,
})

export default router
