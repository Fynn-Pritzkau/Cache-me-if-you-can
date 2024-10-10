<template>
  <div>
    <h1>Willkommen</h1>
    <button class="logout-button" @click="logout">
      Logout
    </button>
  </div>
</template>

<script>
  export default {
  methods: {
    logout() {
      console.log('Logout clicked');
      localStorage.removeItem('user');
      window.location.href = 'http://localhost:9191/api/logout';
    }
  }
}
</script>

<style>
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
</style>