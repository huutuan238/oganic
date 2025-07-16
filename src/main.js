import "./assets/css/bootstrap.min.css"
import "./assets/css/font-awesome.min.css"
import "./assets/css/elegant-icons.css"
import "./assets/css/nice-select.css"
import "./assets/css/jquery-ui.min.css"
import "./assets/css/owl.carousel.min.css"
import "./assets/css/slicknav.min.css"
import "./assets/css/style.css"

import router from './router';

import { createApp } from 'vue'
import App from './App.vue'

createApp(App).use(router).mount('#app')
