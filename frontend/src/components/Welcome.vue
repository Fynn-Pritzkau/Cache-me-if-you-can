<template>
  <div v-if="authenticated">
    <h1>Willkommen, {{ user.name }}</h1>
  </div>
  <div v-else>
    <p>Redirecting to login...</p>
  </div>
</template>

<script>
import axios from "axios";
import {useRouter} from 'vue-router';

export default {
  data() {
    return {
      user: {},
      authenticated: false
    };
  },
  async mounted() {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      this.user = JSON.parse(storedUser);
      this.authenticated = true;
    } else {
      try {
        const response = await axios.get('http://localhost:9191/api/user', {withCredentials: true});
        this.user = response.data;
        localStorage.setItem('user', JSON.stringify(this.user));
        this.authenticated = true;
      } catch {
        this.authenticated = false;
        this.$router.push({name: 'home'});
      }
    }
  }
}
</script>