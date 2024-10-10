<template>
  <div class="container">
    <div class="login-widget">
      <h2 class="widget-title">Login</h2>
      <button class="microsoft-login-button" @click="login">
        <img class="logo" src="./pictures/Microsoft_logo.svg" alt="Microsoft Logo" />
        Mit Microsoft anmelden
      </button>
      <button class="logout-button" @click="logout">
        Logout
      </button>
    </div>
    <img src="./pictures/liquid.jpg" alt="Hier soll bild sein :(" />
  </div>
</template>

<script>
export default {
  name: 'login',
  data() {
    return {
      message: 'Hello Vue.js!'
    }
  },
  methods: {
    login() {
      console.log('Login clicked');
      window.location.href = 'http://localhost:9191/oauth2/authorization/azure';
    },
    logout() {
      console.log('Logout clicked');
      // Clear user data
      localStorage.removeItem('user');
      window.location.href = 'https://login.microsoftonline.com/61ac6a94-31d3-4709-b7b9-a8a96ad74648/oauth2/v2.0/logout?post_logout_redirect_uri=http://localhost:5173/';
    }
  }
}
</script>

<style scoped>

body {
  margin: 0; /* Removes default body margin */
  padding: 0; /* Removes default body padding */
  overflow: hidden; /* Prevents scrolling */
}

.container {
  display: flex;
  height: 100vh;
}

.login-widget {
  width: 50%;
  padding: 10%;
  background-color: #f9f9f9;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.widget-title {
  font-size: 3em;
  margin-bottom: 10px;
  font-family: sans-serif;
  padding-bottom: 50px;
}

.microsoft-login-button {
  display: flex;
  align-items: center;
  background-color: #f25022; /* Microsoft Orange */
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 1em;
  cursor: pointer;
  transition: background-color 0.3s;
}

.microsoft-login-button:hover {
  background-color: #d14324; /* Dunklerer Farbton beim Hover */
}

.logo {
  width: 20px; /* Größe des Logos */
  height: 20px; /* Größe des Logos */
  margin-right: 10px; /* Abstand zwischen Logo und Text */
}

.logout-button {
  display: flex;
  align-items: center;
  background-color: #d14324; /* Darker color for logout */
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 1em;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-top: 20px; /* Add some space above the button */
}

.logout-button:hover {
  background-color: #b33a1f; /* Darker shade on hover */
}

img {
  width: 50%;
  height: 100%;
  object-fit: cover;
}
</style>

