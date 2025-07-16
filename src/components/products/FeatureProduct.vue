<template>
    <!-- Featured Section Begin -->
    <section class="featured spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Featured Product</h2>
                    </div>
                    <div class="featured__controls">
                        <ul>
                            <li class="active" data-filter="*">All</li>
                            <li data-filter=".oranges">Oranges</li>
                            <li data-filter=".fresh-meat">Fresh Meat</li>
                            <li data-filter=".vegetables">Vegetables</li>
                            <li data-filter=".fastfood">Fastfood</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row featured__filter">
                <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat" v-for="product in products">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" :key="product.id"
                            data-setbg="../../assets/img/featured/feature-1.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><router-link to="#"><i class="fa fa-heart"></i></router-link></li>
                                <li><router-link to="#"><i class="fa fa-retweet"></i></router-link></li>
                                <li><router-link to="#"><i class="fa fa-shopping-cart"></i></router-link></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6>
                                <router-link :to="`/product/${product.id}`">{{ product.name }}</router-link>
                            </h6>
                            <h5>{{ product.price }}</h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Featured Section End -->
</template>
<script>
export default {
    data() {
        return {
            products: []
        };
    },
    mounted() {
        fetch('http://localhost:8080/api/products')
            .then(response => response.json())
            .then(data => {
                this.products = data;
            });
    }
};
</script>