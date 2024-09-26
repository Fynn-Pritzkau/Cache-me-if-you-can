<template>
  <div class="container">
    <div class="login-widget">
      <h2 class="widget-title">Login</h2>
      <button class="microsoft-login-button" @click="login">
        <img class="logo" src="./pictures/Microsoft_logo.svg" alt="Microsoft Logo" />
        Mit Microsoft anmelden
      </button>
    </div>
    <img src="./pictures/liquid.jpg" alt="Hier soll bild sein :(" />
  </div>
</template>

<script>
export default {
  methods: {
    login() {
      window.location.href = 'http://localhost:9191/oauth2/authorization/azure';
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

img {
  width: 50%;
  height: 100%;
  object-fit: cover;
}
</style>

