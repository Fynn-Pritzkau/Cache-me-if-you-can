package de.bbk.cachemeifyoucan.controller;

import de.bbk.cachemeifyoucan.dao.CustomerDao;
import de.bbk.cachemeifyoucan.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    // Alle Kunden abrufen
    @GetMapping
    public List<CustomerDao> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    // Neuen Kunden erstellen
    @PostMapping
    public CustomerDao createCustomer(@RequestBody CustomerDao customer)
    {
        return customerRepository.save(customer);
    }

    // Bestehenden Kunden aktualisieren
    @PutMapping("/{id}")
    public CustomerDao updateCustomer(@PathVariable Long id, @RequestBody CustomerDao customerDetails)
    {
        System.out.println("test etstq2e1h2bn: ");
        CustomerDao customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kunde nicht gefunden"));
        customer.setName(customerDetails.getName());
        customer.setVorname(customerDetails.getVorname());
        customer.setGeburtsdatum(customerDetails.getGeburtsdatum());
        customer.setTelefonnummer(customerDetails.getTelefonnummer());
        customer.setAnschrift(customerDetails.getAnschrift());
        customer.setIban(customerDetails.getIban());
        customer.setBic(customerDetails.getBic());
        return customerRepository.save(customer);
    }

    // Kunden löschen
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id)
    {
        customerRepository.deleteById(id);
    }
}
