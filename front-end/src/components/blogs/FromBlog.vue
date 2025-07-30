<script setup lang="js">
import blogService from '@/api/blogService';
import { onMounted, ref } from 'vue';
import BlogItem from './BlogItem.vue';
const blogs = ref([]);

onMounted(async () => {
    blogs.value = await blogService.getBlogs();
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
                    <BlogItem :blog="blog" :show-list-more="false"></BlogItem>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->
</template>