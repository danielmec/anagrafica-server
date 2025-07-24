package com.anagrafica.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anagrafica.demo.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Interfaccia per la gestione dei clienti
    // Estende JpaRepository per le operazioni CRUD
    //non sono necessari metodi aggiuntivi per ora, ma si possono aggiungere query personalizzate se necessario

    // JPA(Jakarta Persistence API) fornisce molti metodi di ricerca basati su convenzioni
    // findByEmail, findByNome, etc.
    Optional<Cliente> findByEmail(String email);

    // Ricerca per nome e cognome (case insensitive) complessa 
    //tradotta poi da Hibernate in SQL sulla tabella "clienti"
      @Query("SELECT c FROM Cliente c WHERE " +
             "LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%')) AND " +
             "LOWER(c.cognome) LIKE LOWER(CONCAT('%', :cognome, '%'))")

      List<Cliente> findByNomeAndCognomeContainingIgnoreCase(
          @Param("nome") String nome, 
          @Param("cognome") String cognome
      );

    // Ricerca per comune (case insensitive)
    List<Cliente> findByComuneContainingIgnoreCase(String comune);
} 
