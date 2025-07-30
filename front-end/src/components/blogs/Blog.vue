<script setup lang="js">
import blogService from '@/api/blogService';
import { onMounted, ref } from 'vue';
import Breadcrumb from '../Breadcrumb.vue';
import BlogItem from './BlogItem.vue';
import BlogSideBar from './BlogSideBar.vue';
let blogs = ref([]);

onMounted(async () => {
    blogs.value = await blogService.getBlogs();
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
                            <BlogItem :blog="blog" :show-list-more="true"></BlogItem>
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