import { createRouter, createWebHistory } from 'vue-router'
import login from "@/components/login.vue";
import Welcome from "@/components/Welcome.vue";
import axios from "axios";

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
      component: Welcome,
      meta: { requiresAuth: true }
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    axios.get('http://localhost:9191/api/user')
        .then(() => {
          console.log('User is authenticated');
          next();
        })
        .catch(() => {
          console.log('User is not authenticated');
          next({ name: 'home' });
        });
  } else {
    next();
  }
});

export default router
