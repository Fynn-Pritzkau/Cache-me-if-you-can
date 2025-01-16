<template>
  <div>
    <h2>CSV Datei Hochladen</h2>
    <form @submit.prevent="uploadCsv">
      <input type="file" @change="handleFile" accept=".csv" />
      <button type="submit">Hochladen</button>
    </form>
    <p v-if="message">{{ message }}</p>
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
    async uploadCsv() {
      if (!this.file) {
        this.message = "Bitte wählen Sie eine Datei aus!";
        return;
      }
      const formData = new FormData();
      formData.append("file", this.file);

      try {
        const response = await axios.post("http://localhost:9191/api/csv/upload", formData, {
          headers: { "Content-Type": "multipart/form-data" },
          withCredentials: true,
        });
        this.message = response.data;
      } catch (error) {
        this.message = "Fehler beim Hochladen: " + error.response?.data || error.message;
      }
    },
  },
};
</script>

<style scoped>
h2 {
  color: #333;
}
</style>