<script setup lang="js">
import { onMounted, ref } from 'vue';
import Breadcrumb from '../Breadcrumb.vue';
import axios from 'axios';
import BlogSideBar from './BlogSideBar.vue';
const blogs = ref([]);

onMounted(async () => {
    await axios.get(`http://localhost:8080/api/blog`)
        .then(res => {
            blogs.value = res.data.data;
        })
})

</script>
<template>
    <Breadcrumb :title="`Blog`"></Breadcrumb>
    <!-- Blog Section Begin -->
    <section class="blog spad">
        <div class="container">
            <div class="row">
                <BlogSideBar></BlogSideBar>
                <div class="col-lg-8 col-md-7 order-md-1 order-1">
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6" v-for="blog in blogs">
                            <div class="blog__item">
                                <div class="blog__item__pic">
                                    <img alt="" :src="blog.imageUrl">
                                </div>
                                <div class="blog__item__text">
                                    <ul>
                                        <li><i class="fa fa-calendar-o"></i> {{ blog.updatedAt }}</li>
                                        <li><i class="fa fa-comment-o"></i> 5</li>
                                    </ul>
                                    <h5><router-link :to="`/blog/${blog.id}`">{{ blog.title }}</router-link></h5>
                                    <p>{{ blog.content }}</p>
                                    <router-link :to="`/blog/${blog.id}`" class="blog__btn">READ MORE <span class="arrow_right"></span></router-link>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="product__pagination blog__pagination">
                                <a href="#">1</a>
                                <a href="#">2</a>
                                <a href="#">3</a>
                                <a href="#"><i class="fa fa-long-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->
</template>