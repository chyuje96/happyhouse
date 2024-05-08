import Vue from "vue";
import Router from "vue-router";
import Index from "../views/IndexView.vue";
import Profile from "../views/ProfileView.vue";
import MainNavbar from "../layout/MainNavbar.vue";
import MainFooter from "../layout/MainFooter.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "index",
      components: { default: Index },
    },
    {
      path: "/qna",
      name: "qna",
      components: {
        default: () => import("@/views/QnaView.vue"),
      },
      redirect: "/qna/list",
      children: [
        {
          path: "list",
          name: "QnaList",
          component: () => import("@/components/qna/QnaList.vue"),
        },
        {
          path: "write",
          name: "QnaRegister",
          component: () => import("@/components/qna/QnaRegister.vue"),
        },
        {
          path: "detail/:no",
          name: "QnaDetail",
          component: () => import("@/components/qna/QnaDetail.vue"),
        },
        {
          path: "modify/:no",
          name: "QnaModify",
          component: () => import("@/components/qna/QnaModify.vue"),
        },
      ],
    },
    {
      path: "/login",
      name: "login",
      component: () => import("@/views/LoginView.vue"),
      props: {
        header: { colorOnScroll: 400 },
      },
    },

    {
      path: "/profile",
      name: "profile",
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
    {
      path: "/register",
      name: "register",
      component: () => import("@/views/RegisterView.vue"),
    },
  ],
  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});
