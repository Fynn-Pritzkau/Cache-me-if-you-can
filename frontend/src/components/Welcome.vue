<template>
  <div>
    <h1>Willkommen, {{ user.name }}</h1>
  </div>
</template>

<script>
import axios from "axios";

export default {
  	data() {
	    return {
      		user: {}
	    };
  	},
  mounted() {
    axios.get('http://localhost:9191/user').then(response => {
      this.user = response.data;
    });
  }
}
</script>
