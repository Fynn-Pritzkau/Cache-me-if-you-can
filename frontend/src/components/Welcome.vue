<template>
  <div class="container">
    <header class="dashboard-header">
      <h1>Dashboard</h1>
      <button class="logout-button" @click="logout">
        Logout
      </button>
    </header>
    <main class="dashboard-content">
      <section class="modules-section">
        <h2>Module</h2>
        <transition name="fade">
          <div class="module-buttons">
            <button class="module-button" @click="goToModule('customer-form')">
              Kundenverwaltung
            </button>
            <button class="module-button" @click="goToModule('plants')">
              Anlagendaten
            </button>
            <button class="module-button" @click="goToModule('csv-upload')">
              Datenimport
            </button>
            <button class="module-button" @click="goToModule('maintenance')">
              Wartungsvorschläge
            </button>
          </div>
        </transition>
      </section>
    </main>
  </div>
</template>

<script>
export default {
  methods: {
    logout() {
      console.log("Logout clicked");
      localStorage.removeItem("user");
      window.location.href = "http://localhost:9191/api/logout";
    },
    goToModule(module) {
      window.location.href = `http://localhost:5173/${module}`;
    },
  },
};
</script>

<style scoped>

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}

.container {
  font-family: sans-serif;
}

/* General Layout */
.dashboard-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #2c3e50;
  color: white;
  padding: 10px 20px;
}

.dashboard-header h1 {
  font-size: 2em;
}

.dashboard-content {
  padding: 20px;
}

.modules-section h2 {
  text-align: center;
  font-size: 1.8em;
  margin-bottom: 20px;
}

/* Module Buttons with Hover and Animation */
.module-buttons {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}

.module-button {
  background-color: #3498db;
  color: white;
  font-size: 1.2em;
  padding: 15px 30px;
  margin: 10px;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.module-button:hover {
  background-color: #2980b9;
  transform: translateY(-5px);
  box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
}

/* Logout Button Styling */
.logout-button {
  background-color: #e74c3c;
  color: white;
  font-size: 1em;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.logout-button:hover {
  background-color: #c0392b;
  transform: scale(1.05);
}
</style>
