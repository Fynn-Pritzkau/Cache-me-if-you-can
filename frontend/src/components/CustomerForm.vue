<template>
  <div>
    <h1 class="main-header">Kundendaten erfassen</h1>

    <form @submit.prevent="saveCustomer">
      <div class="name-container">
        <label>Name:</label>
        <input v-model="customer.name" required />

        <label>Vorname:</label>
        <input v-model="customer.vorname" required>
      </div>

      <div class="birthday-container">
        <label>Geburtsdatum:</label>
        <input v-model="customer.geburtsdatum" type="date" required />
      </div>

      <div class="phone-container">
        <label>Telefonnummer:</label>
        <input v-model="customer.telefonnummer" required />
      </div>

      <div class="address-container">
        <label>Anschrift:</label>
        <input v-model="customer.anschrift" required />
      </div>

      <div class="bankinfo-container">
        <label>IBAN:</label>
        <input v-model="customer.iban" required />
        <label>BIC (nur Ausland):</label>
        <input v-model="customer.bic" />
      </div>

      <button type="submit">{{ isEditMode ? 'Kundendaten aktualisieren' : 'Kundendaten speichern' }}</button>
    </form>

    <!-- Tabelle mit allen Kunden -->
    <h3>Alle Kunden</h3>
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
          <button @click="editCustomer(cust)">Bearbeiten</button>
          <button @click="deleteCustomer(cust.id)">Löschen</button>
        </td>
      </tr>
      </tbody>
    </table>
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
      isEditMode: false, // Flag, ob wir einen Datensatz bearbeiten oder neu anlegen
      currentCustomerId: null // Die ID des aktuell bearbeiteten Kunden
    };
  },
  methods: {
    // Kunden aus der API laden
    async fetchCustomers() {
      try {
        const response = await axios.get('http://localhost:9191/api/customers');
        this.customers = response.data;
      } catch (error) {
        console.error("Fehler beim Laden der Kunden:", error);
      }
    },

    // Neue Kunden speichern oder bestehende aktualisieren
    async saveCustomer() {
      try {
        if (this.isEditMode) {
          // Kunden aktualisieren
          await axios.put(`http://localhost:9191/api/customers/${this.currentCustomerId}`, this.customer);
          alert("Kundendaten erfolgreich aktualisiert!");
        } else {
          // Neuen Kunden hinzufügen
          await axios.post('http://localhost:9191/api/customers', this.customer);
          alert("Kundendaten erfolgreich gespeichert!");
        }
        this.resetForm();
        this.fetchCustomers(); // Nach dem Speichern die Liste aktualisieren
      } catch (error) {
        console.error("Fehler beim Speichern der Kundendaten:", error);
      }
    },

    editCustomer(customer) {
      this.customer = {...customer}; // Werte in das Formular laden
      this.isEditMode = true; // Bearbeitungsmodus aktivieren
      this.currentCustomerId = customer.id; // ID des zu bearbeitenden Kunden speichern
    },

    // Einen Kunden löschen
    async deleteCustomer(customerId) {
      try {
        await axios.delete(`http://localhost:9191/api/customers/${customerId}`);
        alert("Kunde erfolgreich gelöscht!");
        this.fetchCustomers(); // Nach dem Löschen die Liste aktualisieren
      } catch (error) {
        console.error("Fehler beim Löschen des Kunden:", error);
      }
    },

    // Das Formular zurücksetzen
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
      this.isEditMode = false; // Bearbeitungsmodus deaktivieren
      this.currentCustomerId = null; // Die aktuelle Kunden-ID zurücksetzen
    }
  },

  // Beim Laden der Seite alle Kunden abrufen
  async created() {
    try {
      const user = await axios.get('http://localhost:9191/api/user', { withCredentials: true });
      console.log("Benutzer ist authentifiziert:", user.data);
      await this.fetchCustomers(); // Kunden laden
    } catch (error) {
      console.error("Benutzer ist nicht authentifiziert:", error);
      alert("Bitte melden Sie sich zuerst an.");
      window.location.href = 'http://localhost:5173/login'; // Zum Login umleiten
    }
  }
};
</script>

<style scoped>

.main-header {
  font-size: 3em;
  margin-bottom: 40px;
  width: 100%;
  text-align: center;
  font-family: sans-serif;
}

table {
  width: 100%;
  border-collapse: collapse;
}

table th, table td {
  padding: 10px;
  border: 1px solid #ddd;
}

button {
  padding: 5px 10px;
  margin-right: 5px;
  font-size: 20px;
  border-style: solid;
  border-radius: 5px;
}

button:hover {
  background-color: #f0f0f0;
}

div {
  font-family: sans-serif;
}

input {
  padding: 5px;
  margin-bottom: 10px;
  width: 100%;
  border-radius: 5px;
  border-color: #848484;
  border-style: solid;
  font-size: 20px;
}

.bankinfo-container, .address-container, .phone-container, .birthday-container, .name-container {
  margin-bottom: 5px;
  margin-left: 5px;
  font-size: 20px;
  align-items: center;
  justify-content: space-between;
  display: flex;
  flex-wrap: wrap;
}

</style>
