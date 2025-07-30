<script setup>
import Breadcrumb from '../Breadcrumb.vue';
import ProductItem from './ProductItem.vue';
</script>
<template>
    <Breadcrumb :title="this.$route.query.q || this.$route.query.categoryId"></Breadcrumb>
    <section class="search-product spad">
        <div class="container">
            <div class="row featured__filter">
                <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat" v-for="product in products">
                    <ProductItem :product="product"></ProductItem>
                </div>
            </div>
            <div class="product__pagination">
                <a href="#" v-show="currentPage !== 1" @click.prevent="goToPage(currentPage - 1)"><i
                        class="fa fa-long-arrow-left"></i></a>
                <a href="#" v-for="page in totalPages" :class="{ active: currentPage === page }"
                    @click.prevent="goToPage(page)">{{ page }}</a>
                <a href="#" v-show="currentPage !== totalPages" @click.prevent="goToPage(currentPage + 1)"><i
                        class="fa fa-long-arrow-right"></i></a>
            </div>
        </div>
    </section>
</template>
<script>
import axios from 'axios';

export default {
    data() {
        return {
            products: [],
            totalElements: [],
            totalPages: [],
            currentPage: 1,
        }
    },
    mounted() {
        this.getData(this.currentPage);
    },
    watch: {
        '$route.query': {
            handler() {
                this.getData(1)
            },
            deep: true,
            immediate: true
        }
    },
    methods: {
        getData(page) {
            const queryValue = this.$route.query.q || '';
            const categoryId = this.$route.query.categoryId || '';
            axios
                .get(`http://localhost:8080/api/products/search?q=${queryValue}&categoryId=${categoryId}&page=${page}`)
                .then(response => {
                    const data = response.data;
                    this.totalElements = data.totalElements;
                    this.totalPages = data.totalPages;
                    this.products = data.content;
                    this.currentPage = page;
                })
        },
        goToPage(page) {
            if (page !== this.currentPage) {
                this.getData(page);
            }
        }
    }
}
</script>