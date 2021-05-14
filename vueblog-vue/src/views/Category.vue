<template>
    <div>
        <Header/>
        <div class="m-category-area" v-for="category_blog in category_blog_list">
            <div class="m-category-name">
                <h2>{{category_blog.category}}</h2>
            </div>
            <div class="m-category-list" v-for="blog in category_blog.blogs">
                <ul>
                    <router-link class="router-link-active" :to="{name: 'BlogDetail',params: {blogId:blog.id}}">
                        {{ blog.title }}
                    </router-link>
                </ul>
            </div>
        </div>
        <Footer/>
    </div>
</template>

<script>
    import Header from "../components/Header";
    import Footer from "../components/Footer";

    export default {
        name: "Category",
        components : {Header, Footer},
        data(){
            return {
                categories: [],
                category_blog_list: []
            }
        },
        created: function () {
            const _this = this;
            this.$axios.get('/categories').then(res => {
                _this.categories = res.data.data;
                _this.categories.forEach(function (element, index, array) {
                    _this.$axios.get('/blog/cid/' + element.cid).then(res => {
                        _this.category_blog_list.push({
                            category: element.category,
                            blogs: res.data.data,
                        })
                    })
                });
            });
        }
    }
</script>

<style scoped>
    /* 分类区样式 */
    .m-category-area{
        max-width: 700px;
        margin: 0 auto; /*居中*/
        line-height: 2;
    }
    /* 分类名样式 */
    .m-category-name{
        margin: 0;
        font-family: "Times New Roman",Georgia,serif;
    }
    /* router-link style */
    .router-link-active {
        text-decoration: none;
        font-family: "SFMono-Regular", Consolas, "Liberation Mono", Menlo, Courier, monospace;
        font-size: 20px;
        color: #323330;
    }
</style>