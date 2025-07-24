package com.anagrafica.demo.controller;

import com.anagrafica.demo.entity.Cliente;
import com.anagrafica.demo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clienti") //endpoint base 
public class ClienteController {
    
    //fanno uso tutti i metodi del servizio ClienteService
    //che gestisce la logica di business e l'interazione con il repository
    @Autowired
    private ClienteService clienteService;
    
    /**
     * GET /api/clienti -recupera tutti i clienti
     */
    @GetMapping // risponde a GET /api/clienti
    public ResponseEntity<List<Cliente>> getAllClienti() {

        List<Cliente> clienti = clienteService.getAllClienti();
        return ResponseEntity.ok(clienti);

    }
    
    /**
     * GET /api/clienti/{id} - recupera un cliente con ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {

        Optional<Cliente> cliente = clienteService.getClienteById(id);
        
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * POST /api/clienti - crea un nuovo cliente
     */
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {

        try {
            Cliente nuovoCliente = clienteService.saveCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuovoCliente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * PUT /api/clienti/{id} - Aggiorna un cliente esistente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(
            @PathVariable Long id, 
            @Valid @RequestBody Cliente cliente) {
        try {
            Cliente clienteAggiornato = clienteService.updateCliente(id, cliente);
            return ResponseEntity.ok(clienteAggiornato);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * DELETE /api/clienti/{id} - elimina un cliente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * GET /api/clienti/search?nome=...&cognome=... - Ricerca clienti
     */
    @GetMapping("/search")
    public ResponseEntity<List<Cliente>> searchClienti(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cognome) {
        List<Cliente> clienti = clienteService.searchClienti(nome, cognome);
        return ResponseEntity.ok(clienti);
    }
    
    /**
     * GET /api/clienti/comune/{comune} - Clienti per comune
     */
    @GetMapping("/comune/{comune}")
    public ResponseEntity<List<Cliente>> getClientiByComune(@PathVariable String comune) {
        List<Cliente> clienti = clienteService.getClientiByComune(comune);
        return ResponseEntity.ok(clienti);
    }
}