import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import BlogEdit from '../views/BlogEdit.vue'
import BlogDetail from "../views/BlogDetail";
import Blog from "../views/Blog";
import Archive from "../views/Archive";
import GuestBook from "../views/GuestBook";
import Category from "../views/Category";


Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'Index',
        redirect: {name: "Blog"}
    },
    {
        path: '/blog',
        name: 'Blog',
        component: Blog,
        meta:{
            title: "Yongxin's Tech Blog"
        }
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
        meta:{
            title: "登录"
        }
    },
    {
        path: '/blog/add',
        name: 'BlogAdd',
        component: BlogEdit,
        meta:{
            requireAuth: true, //需要登录才能访问
            title: '发表博客'
        }
    },
    {
        path: '/blog/:blogId',
        name: 'BlogDetail',
        component: BlogDetail
    },
    {
        path: '/blog/:blogId/edit',
        name: 'BlogEdit',
        component: BlogEdit,
        meta: {
            requireAuth: true //需要登录才能访问
        }
    },
    {
        path: '/blog/delete/:blogId',
        meta: {
            requireAuth: true //需要登录才能访问
        }
    },
    {
        path: '/archive',
        name: 'Archive',
        component: Archive,
        meta:{
            title: '归档'
        }
    },
    {
        path: '/comment',
        name: 'Comment',
        component: GuestBook,
        meta:{
            title: "留言"
        }
    },
    {
        path: '/category',
        name: 'Category',
        component: Category,
        meta:{
            title: "分类"
        }
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
