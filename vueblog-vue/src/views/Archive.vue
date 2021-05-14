<template>
    <div>
        <!-- 引入Header组件 -->
        <Header/>
        <div class="m-archive">
            <div class="m-archive-list" v-for="item in blogs">
               <div class="m-year"><h2>{{item.year}}</h2></div>
                <div class="m-blog-list" v-for="blog in item.list">
                    <ul class="m-blog">
                        {{blog.created.substr(5,5)}}
                        <router-link class="router-link-active" :to="{name: 'BlogDetail',params: {blogId:blog.id}}">
                            {{ blog.title }}
                        </router-link>
                    </ul>
                </div>
            </div>
        </div>
        <!-- 引入Footer组件 -->
        <Footer/>
    </div>
</template>

<script>
    import Header from "../components/Header";
    import Footer from "../components/Footer";

    export default {
        name: "Archive",
        components: {Footer, Header},
        data(){
            return {
                blogList : [],
                years: [],
                blogs: [],
            }
        },
        methods: {
            initBlogs(res){
                const _this = this;
                _this.blogList = res.data.data;
                _this.blogList.forEach(function(element, index, array){
                    if (_this.years.indexOf(element.created.substr(0, 4)) === -1){
                        _this.years.push(element.created.substr(0, 4));
                    }
                });
                _this.years.forEach(function(element, index, array){
                    _this.blogs.push({"year": element,list: []})
                });
                _this.blogs.forEach(function (element, index, array) {
                    _this.blogList.forEach(function (obj, index, array) {
                        if (obj.created.substr(0, 4) === element.year){
                            element.list.push(obj)
                        }
                    })
                });
            }
        },
        created: function () {
            this.$axios.get('/archive').then(res => {
                this.initBlogs(res);
            })
        }
    }
</script>

<style scoped>
    /* 归档样式 */
    .m-archive{
        max-width: 800px;
        margin: 0 auto; /*居中*/
        line-height: 2;
    }
    /* 年份样式 */
    .m-year h2{
        margin: 0;
        font: bold 25px/1.1 "ff-tisa-web-pro", Cambria, "Times New Roman", Georgia, Times, sans-serif;
    }
    /* blog list style */
    ul{
        display: block;
        list-style-type: disc;
        margin-block-start: 1em;
        margin-block-end: 1em;
        margin-inline-start: 0px;
        margin-inline-end: 0px;
        padding-inline-start: 40px;
    }

    .m-blog{
        font-size: 20px;
        text-decoration: none;
    }

    /* router-link style */
    .router-link-active {
        text-decoration: none;
        font-family: "SFMono-Regular", Consolas, "Liberation Mono", Menlo, Courier, monospace;
        color: #323330;
    }
</style>