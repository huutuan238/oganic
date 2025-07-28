import "./assets/css/bootstrap.min.css"
import "./assets/css/font-awesome.min.css"
import "./assets/css/elegant-icons.css"
import "./assets/css/nice-select.css"
import "./assets/css/jquery-ui.min.css"
import "./assets/css/owl.carousel.min.css"
import "./assets/css/slicknav.min.css"
import "./assets/css/style.css"
import 'vue-toast-notification/dist/theme-bootstrap.css';

import router from './router';

import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from "pinia"
import { BootstrapVue3 } from "bootstrap-vue-3"
import ToastPlugin from 'vue-toast-notification';
const app = createApp(App)
app.use(BootstrapVue3)
app.use(ToastPlugin)
app.use(router)
app.use(createPinia())
app.mount('#app')
// createApp(App).use(router).use(createPinia()).mount('#app')
