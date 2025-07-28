<script setup>
import axios from 'axios';
import { useRouter } from 'vue-router';
import Breadcrumb from '../Breadcrumb.vue';
import { userStore } from '@/store/user';
import { computed, ref } from 'vue';

const router = useRouter();
const user = userStore();

const password = ref('');
const confirmPassword = ref('');
const formValidated = ref(false);

const isPasswordMatch = computed(() => {
  return password.value === confirmPassword.value;
});

const register = async (e) => {
  e.preventDefault();
  formValidated.value = true;

  if (!isPasswordMatch.value) {
    return;
  }

  const form = document.querySelector('#register-form');
  const userData = Object.fromEntries(new FormData(form).entries());

  await axios.post('http://localhost:8080/api/users/register', JSON.stringify(userData), {
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(res => {
    user.login(res.data.data);
    router.push('/');
  }).catch(error => {
    console.log(error);
  });
};
</script>

<template>
  <Breadcrumb :title="`Register`" />

  <section class="register">
    <div class="container">
      <div class="checkout__form mt-3">
        <b-form
          id="register-form"
          @submit="register"
          :validated="formValidated"
          novalidate
        >
          <div class="row">
            <div class="col-lg-8 col-md-6 mx-auto">
              <div class="row">
                <div class="col-lg-6">
                  <b-form-group label="First Name" label-for="firstName" label-cols-sm="12" label-align-sm="left">
                    <b-form-input id="firstName" name="firstName" required />
                    <b-form-invalid-feedback>First name is required.</b-form-invalid-feedback>
                  </b-form-group>
                </div>

                <div class="col-lg-6">
                  <b-form-group label="Last Name" label-for="lastName">
                    <b-form-input id="lastName" name="lastName" required />
                    <b-form-invalid-feedback>Last name is required.</b-form-invalid-feedback>
                  </b-form-group>
                </div>
              </div>

              <b-form-group label="Email" label-for="email">
                <b-form-input
                  id="email"
                  type="email"
                  name="email"
                  required
                />
                <b-form-invalid-feedback>Valid email is required.</b-form-invalid-feedback>
              </b-form-group>

              <b-form-group label="Password" label-for="password">
                <b-form-input
                  id="password"
                  type="password"
                  name="password"
                  v-model="password"
                  required
                />
                <b-form-invalid-feedback>Password is required.</b-form-invalid-feedback>
              </b-form-group>

              <b-form-group label="Confirm Password" label-for="confirmPassword">
                <b-form-input
                  id="confirmPassword"
                  type="password"
                  name="confirm-password"
                  v-model="confirmPassword"
                  :state="isPasswordMatch ? true : false"
                  required
                />
                <b-form-invalid-feedback>Passwords must match.</b-form-invalid-feedback>
              </b-form-group>

              <b-form-group label="Phone" label-for="phone">
                <b-form-input id="phone" name="phone" required />
                <b-form-invalid-feedback>Phone number is required.</b-form-invalid-feedback>
              </b-form-group>

              <b-form-group label="Address" label-for="address">
                <b-form-input id="address" name="address" required />
                <b-form-invalid-feedback>Address is required.</b-form-invalid-feedback>
              </b-form-group>

              <div class="register-button mb-3">
                <b-button type="submit" variant="primary" class="site-btn">Register</b-button>
              </div>
            </div>
          </div>
        </b-form>
      </div>
    </div>
  </section>
</template>
