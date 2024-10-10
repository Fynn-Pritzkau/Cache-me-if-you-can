import { createRouter, createWebHistory } from 'vue-router';
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
});

const isAuthenticated = async () => {
  try {
    await axios.get("http://localhost:9191/api/user", { withCredentials: true });
    return true;
  } catch {
    return false;
  }
};

router.beforeEach(async (to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    const authenticated = await isAuthenticated();
    if (authenticated) {
      next();
    } else {
      next({ name: 'home' });
    }
  } else {
    next();
  }
});

export default router;