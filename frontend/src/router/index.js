import { createRouter, createWebHistory } from 'vue-router'
import login from "@/components/login.vue";
import Welcome from "@/components/Welcome.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: login
    },
    {
      path: '/Welcome',
      name: 'welcome',
      component: Welcome
    }
  ]
})

export default router
