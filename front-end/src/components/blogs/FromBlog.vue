<script setup lang="js">
import { onMounted, ref } from 'vue';
import BlogItem from './BlogItem.vue';
import axios from 'axios';
const blogs = ref([]);

onMounted(async () => {
    await axios.get(`http://localhost:8080/api/blog`)
        .then(res => {
            blogs.value = res.data.data;
        })
})

</script>

<template>
    <!-- Blog Section Begin -->
    <section class="from-blog spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title from-blog__title">
                        <h2>From The Blog</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-6" v-for="blog in blogs">
                    <BlogItem :blog="blog" :show-list-more="false" ></BlogItem>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->
</template>