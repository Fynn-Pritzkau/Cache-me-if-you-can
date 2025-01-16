package de.bbk.cachemeifyoucan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import de.bbk.cachemeifyoucan.dao.CustomerDao;
import de.bbk.cachemeifyoucan.repository.CustomerRepository;

@RestController
@RequestMapping("/api/csv")
public class CsvController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/upload")
    public String uploadCsv(@RequestParam("file") MultipartFile file)
    {
        try (BufferedReader reader =
                     new BufferedReader(
                            new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)))
        {

            List<CustomerDao> customers = new ArrayList<>();
            String line;
            // Skip header
            reader.readLine();

            while ((line = reader.readLine()) != null)
            {
                String[] data = line.split(",");
                CustomerDao customer = new CustomerDao();
                customer.setName(data[0]);
                customer.setVorname(data[1]);
                customer.setGeburtsdatum(LocalDate.parse(data[2]));
                customer.setTelefonnummer(data[3]);
                customer.setAnschrift(data[4]);
                customer.setIban(data[5]);
                customer.setBic(data[6]);
                customers.add(customer);
            }
            customerRepository.saveAll(customers);
            return "Datei erfolgreich verarbeitet!";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Fehler beim Verarbeiten der Datei: " + e.getMessage();
        }
    }
}