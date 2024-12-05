<template>
  <div>
    <h2>Kundendaten erfassen</h2>

    <!-- Formular für das Hinzufügen oder Bearbeiten eines Kunden -->
    <form @submit.prevent="saveCustomer">
      <label>Name:</label>
      <input v-model="customer.name" required />

      <label>Vorname:</label>
      <input v-model="customer.vorname" required />

      <label>Geburtsdatum:</label>
      <input v-model="customer.geburtsdatum" type="date" required />

      <label>Telefonnummer:</label>
      <input v-model="customer.telefonnummer" required />

      <label>Anschrift:</label>
      <input v-model="customer.anschrift" required />

      <label>IBAN:</label>
      <input v-model="customer.iban" required />

      <label>BIC (nur Ausland):</label>
      <input v-model="customer.bic" />

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

export default {
  data() {
    return {
      customers: [], // Alle Kunden
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

    // Den Kunden aus der Tabelle bearbeiten (wird in das Formular geladen)
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
  created() {
    this.fetchCustomers();
  }
};
</script>

<style scoped>
/* Füge hier dein CSS hinzu */
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
}

button:hover {
  background-color: #f0f0f0;
}
</style>
