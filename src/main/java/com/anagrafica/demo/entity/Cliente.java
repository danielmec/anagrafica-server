package com.anagrafica.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clienti")
public class Cliente {

    // Attributi campi tabella "clienti"
    //Utilizzo di JPA per la gestione della persistenza
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    @Size(min = 2, max = 50, message = "Il nome deve essere tra 2 e 50 caratteri")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    @Size(min = 2, max = 50, message = "Il cognome deve essere tra 2 e 50 caratteri")
    @Column(nullable = false)
    private String cognome;

    private String indirizzo;

    private String localita;

    private String comune;

    @Size(max = 2, message = "La provincia deve essere di 2 caratteri")
    private String provincia;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "L'email deve essere valida")
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(columnDefinition = "TEXT")
    private String note;


    // Costruttore vuoto per JPA
    public Cliente() {
    }

    public Cliente(String nome, String cognome, String indirizzo, String localita, String comune, String provincia, String email, String note) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.localita = localita;
        this.comune = comune;
        this.provincia = provincia;
        this.email = email;
        this.note = note;
    }


    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getLocalita() {
        return localita;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
