<script setup>
import axios from 'axios';
import { useRouter } from 'vue-router';
import Breadcrumb from '../Breadcrumb.vue';
import { userStore } from '@/store/user';
const router = useRouter();
const user = userStore()

const register = async (e) => {
    e.preventDefault();
    const form = document.querySelector('#register-form');
    const userData = Object.fromEntries(new FormData(form).entries());
    await axios.post('http://localhost:8080/api/users/register', JSON.stringify(userData), {
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        user.login(res.data.data);
        router.push('/')
    })
        .catch(error => {
            console.log(error)
        })
}

</script>
<template>
    <Breadcrumb :title="`Register`"></Breadcrumb>
    <section class="register">
        <div class="container">
            <div class="checkout__form mt-3">
                <form id="register-form" @submit="register">
                    <div class="row">
                        <div class="col-lg-8 col-md-6 mx-auto">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>Fist Name<span>*</span></p>
                                        <input type="text" name="firstName">
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>Last Name<span>*</span></p>
                                        <input type="text" name="lastName">
                                    </div>
                                </div>
                            </div>
                            <div class="checkout__input">
                                <p>Email<span>*</span></p>
                                <input type="text" placeholder="oganic@gmail.com" name="email"
                                    class="checkout__input__add">
                            </div>
                            <div class="checkout__input">
                                <p>Password<span>*</span></p>
                                <input type="password" name="password">
                            </div>
                            <div class="checkout__input">
                                <p>Confirm Password<span>*</span></p>
                                <input type="password" name="confirm-password">
                            </div>
                            <div class="checkout__input">
                                <p>Phone<span>*</span></p>
                                <input type="text" name="phone" class="checkout__input__add">
                            </div>
                            <div class="checkout__input">
                                <p>Address<span>*</span></p>
                                <input type="text" placeholder="Street Address" name="address"
                                    class="checkout__input__add">
                            </div>
                            <div class="register-button mb-3">
                                <button type="submit" class="site-btn">Register</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</template>