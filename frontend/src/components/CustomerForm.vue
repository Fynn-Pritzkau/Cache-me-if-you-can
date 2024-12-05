<template>
  <div class="container">
    <div class="main-header">
      <h1>Kundendaten verwalten</h1>
      <button @click="goToModule('welcome')">Dashboard</button>
    </div>

    <div class="form-container">
      <h2>{{ isEditMode ? 'Kundendaten bearbeiten' : 'Neuen Kunden hinzufügen' }}</h2>
      <form @submit.prevent="saveCustomer">
        <fieldset>
          <legend>Persönliche Daten</legend>
          <label for="name">Name:</label>
          <input id="name" v-model="customer.name" required />

          <label for="vorname">Vorname:</label>
          <input id="vorname" v-model="customer.vorname" required />

          <label for="geburtsdatum">Geburtsdatum:</label>
          <input id="geburtsdatum" type="date" v-model="customer.geburtsdatum" required />
        </fieldset>

        <fieldset>
          <legend>Kontaktinformationen</legend>
          <label for="telefonnummer">Telefonnummer:</label>
          <input id="telefonnummer" v-model="customer.telefonnummer" required />

          <label for="anschrift">Anschrift:</label>
          <input id="anschrift" v-model="customer.anschrift" required />
        </fieldset>

        <fieldset>
          <legend>Bankdaten</legend>
          <label for="iban">IBAN:</label>
          <input id="iban" v-model="customer.iban" required />

          <label for="bic">BIC (nur Ausland):</label>
          <input id="bic" v-model="customer.bic" />
        </fieldset>

        <div class="form-buttons">
          <button type="submit">{{ isEditMode ? 'Aktualisieren' : 'Speichern' }}</button>
          <button type="button" @click="resetForm" v-if="isEditMode">Abbrechen</button>
        </div>
      </form>
    </div>

    <div class="table-container">
      <h2>Alle Kunden</h2>
      <table>
        <thead>
        <tr>
          <th>Name</th>
          <th>Vorname</th>
          <th>Geburtsdatum</th>
          <th>Telefonnummer</th>
          <th>Aktionen</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(cust, index) in customers" :key="cust.id">
          <td>{{ cust.name }}</td>
          <td>{{ cust.vorname }}</td>
          <td>{{ cust.geburtsdatum }}</td>
          <td>{{ cust.telefonnummer }}</td>
          <td>
            <button @click="editCustomer(cust)">✏️ Bearbeiten</button>
            <button @click="deleteCustomer(cust.id)">🗑️ Löschen</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>


<script>
import axios from 'axios';

axios.defaults.withCredentials = true;

export default {
  data() {
    return {
      customers: [],
      customer: {
        name: '',
        vorname: '',
        geburtsdatum: '',
        telefonnummer: '',
        anschrift: '',
        iban: '',
        bic: ''
      },
      isEditMode: false,
      currentCustomerId: null
    };
  },
  methods: {
    async fetchCustomers() {
      try {
        const response = await axios.get('http://localhost:9191/api/customers');
        this.customers = response.data;
      } catch (error) {
        console.error("Fehler beim Laden der Kunden:", error);
      }
    },

    async saveCustomer() {
      try {
        if (this.isEditMode) {
          await axios.put(`http://localhost:9191/api/customers/${this.currentCustomerId}`, this.customer);
          alert("Kundendaten erfolgreich aktualisiert!");
        } else {
          await axios.post('http://localhost:9191/api/customers', this.customer);
          alert("Kundendaten erfolgreich gespeichert!");
        }
        this.resetForm();
        this.fetchCustomers();
      } catch (error) {
        console.error("Fehler beim Speichern der Kundendaten:", error);
      }
    },

    editCustomer(customer) {
      this.customer = {...customer};
      this.isEditMode = true;
      this.currentCustomerId = customer.id;
    },

    async deleteCustomer(customerId) {
      try {
        await axios.delete(`http://localhost:9191/api/customers/${customerId}`);
        alert("Kunde erfolgreich gelöscht!");
        this.fetchCustomers();
      } catch (error) {
        console.error("Fehler beim Löschen des Kunden:", error);
      }
    },

    resetForm() {
      this.customer = {
        name: '',
        vorname: '',
        geburtsdatum: '',
        telefonnummer: '',
        anschrift: '',
        iban: '',
        bic: ''
      };
      this.isEditMode = false;
      this.currentCustomerId = null;
    },

    goToModule(module) {
      window.location.href = `http://localhost:5173/${module}`;
    },
  },
  async created() {
    await this.fetchCustomers();
  },
};
</script>

<style scoped>

.container {
  margin: 0 auto;
  font-family: sans-serif;
}

.main-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #2c3e50;
  color: white;
  padding: 10px 20px;
}

.main-header h1 {
  font-size: 2em;
}

.form-container {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 30px;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
}

fieldset {
  font-size: 20px;
  margin-bottom: 15px;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 15px;
}

legend {
  font-weight: bold;
  padding: 0 10px;
}

input {
  width: 100%;
  padding-bottom: 7px;
  padding-top: 7px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 20px;
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

.table-container {
  margin-top: 30px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

table th, table td {
  text-align: left;
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

</style>
