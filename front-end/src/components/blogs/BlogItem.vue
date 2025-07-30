<script setup>
const props = defineProps(['blog', 'showListMore'])
const blog = props.blog
</script>
<template>
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
            <router-link :to="`/blog/${blog.id}`" class="blog__btn" v-if="props.showListMore">READ MORE <span
                    class="arrow_right"></span></router-link>
        </div>
    </div>
</template>