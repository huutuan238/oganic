<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import Breadcrumb from '../Breadcrumb.vue';
const router = useRouter()
const errorMsg = ref("");
const login = async (e) => {
    e.preventDefault();
    const form = document.querySelector('#loginForm');
    const userData = Object.fromEntries(new FormData(form).entries());
    await axios.post('http://localhost:8080/api/users/login', JSON.stringify(userData), {
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        localStorage.setItem('token', res.data.data.token);
        localStorage.setItem('userId', res.data.data.id);
        localStorage.setItem('email', res.data.data.email);
        router.push('/')
    }).catch(error => {
        errorMsg.value = error.response.data.message;
    })
}
</script>
<template>
    <Breadcrumb :title="`Login`"></Breadcrumb>
    <h3 class="text-center" style="color: #7fad39;">
        Login
    </h3>
    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <div class="alert alert-danger" role="alert" v-show="errorMsg.length > 0">
                            {{ errorMsg }}
                        </div>
                        <form id="loginForm" @submit="login">
                            <div class="form-group">
                                <label for="email">
                                    Email
                                </label>
                                <input type="email" class="form-control" name="email" id="email" placeholder="Email"
                                    required />
                            </div>
                            <div class="form-group">
                                <label for="password">
                                    Password
                                </label>
                                <input type="password" class="form-control" id="password" name="password"
                                    placeholder="Password" required />
                            </div>
                            <button class="site-btn">
                                Login
                            </button>
                        </form>
                        <p class="mt-3">
                            Not registered?
                            <router-link :to="'/register'" style="color: #7fad39;">Create an account</router-link>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>