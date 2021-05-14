<template>
    <div>
        <Header/>
        <div  class="m-guest-book">
            <div class="m-comment-area">
                <div id="gitalk-container"></div>
            </div>
        </div>
        <Footer/>
    </div>
</template>

<script>
    import Header from "../components/Header";
    import Footer from "../components/Footer";
    import 'gitalk/dist/gitalk.css'
    import Gitalk from 'gitalk'

    export default {
        name: "GuestBook",
        components: {Header, Footer},
        data(){
            return {
                gitalk : new Gitalk({
                    id: 'guestBook',
                    clientID: 'Your client id',
                    clientSecret: 'Your client Secret',
                    repo: 'your repo name',
                    owner: 'owner Name',//
                    admin: ['admin Name'],///github用户名
                    perPage: 20,
                    distractionFreeMode: false
                }),
            }
        },
        mounted () {
            this.gitalk.render("gitalk-container");
        },

    }
</script>

<style scoped>
    .m-guest-book {
        max-width: 800px;
        margin: 0 auto;
    }
    .m-comment-area {
        width: 800px;
    }
</style>