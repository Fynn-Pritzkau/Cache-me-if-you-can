package de.bbk.cachemeifyoucan.dao;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customer")
public class CustomerDao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String vorname;
    private LocalDate geburtsdatum;
    private String telefonnummer;
    private String anschrift;
    private String iban;
    private String bic;  // Optional für Ausland

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getVorname()
    {
        return vorname;
    }

    public void setVorname(String vorname)
    {
        this.vorname = vorname;
    }

    public LocalDate getGeburtsdatum()
    {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum)
    {
        this.geburtsdatum = geburtsdatum;
    }

    public String getTelefonnummer()
    {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer)
    {
        this.telefonnummer = telefonnummer;
    }

    public String getAnschrift()
    {
        return anschrift;
    }

    public void setAnschrift(String anschrift)
    {
        this.anschrift = anschrift;
    }

    public String getIban()
    {
        return iban;
    }

    public void setIban(String iban)
    {
        this.iban = iban;
    }

    public String getBic()
    {
        return bic;
    }

    public void setBic(String bic)
    {
        this.bic = bic;
    }
}
