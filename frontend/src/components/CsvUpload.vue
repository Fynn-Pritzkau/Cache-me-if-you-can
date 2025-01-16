<template>
  <div class="container">
       <div class="main-header">
            <h1>CSV Datei Hochladen</h1>
            <button @click="goToModule('welcome')">Dashboard</button>
       </div>
       <div class="content">
          <form @submit.prevent="uploadCsv">
           <input type="file" @change="handleFile" accept=".csv" />
           <button type="submit">Hochladen</button>
          </form>
       </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      file: null,
      message: "",
    };
  },
  methods: {
    handleFile(event) {
      this.file = event.target.files[0];
    },

     goToModule(module) {
       window.location.href = `http://localhost:5173/${module}`;
     },

    async uploadCsv() {
      if (!this.file) {
        alert("Bitte wählen Sie eine Datei aus!");
        return;
      }
      const formData = new FormData();
      formData.append("file", this.file);

      try {
        const response = await axios.post("http://localhost:9191/api/csv/upload", formData, {
          headers: { "Content-Type": "multipart/form-data" },
          withCredentials: true,
        });
        alert("" + response.data);
      } catch (error) {
        alert("Fehler beim Hochladen: " + error.response?.data || error.message);
      }
    },
  },
};
</script>

<style scoped>
h2 {
  color: #333;
}

.main-header {
     display: flex;
     justify-content: space-between;
     align-items: center;
     background-color: #2c3e50;
     color: white;
     padding: 10px 20px;
     margin-bottom: 30px;
}

.container {
     margin: 0 auto;
     font-family: sans-serif;
}

.content {
     background-color: #f9f9f9;
     padding: 20px;
     border-radius: 10px;
     margin-bottom: 30px;
}

button {
     padding: 10px 15px;
     border: none;
     border-radius: 5px;
     cursor: pointer;
}

button:hover {
     background-color: #007bff;
     color: white;
}
</style>