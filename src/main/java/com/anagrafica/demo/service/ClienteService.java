package com.anagrafica.demo.service;

import com.anagrafica.demo.entity.Cliente;
import com.anagrafica.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    /**
     * Recupera tutti i clienti facendo uso del repository 
     * metodo findAll() gia fornito da JpaRepository simile a SELECT * FROM clienti
     */
    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }
    
    /**
     * Recupera un cliente per ID
     */
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }
    
    /**
     * Salva un nuovo cliente
     */
    public Cliente saveCliente(Cliente cliente) {
        //verifica
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new RuntimeException("Email già esistente: " + cliente.getEmail());
        }
        //INSERT implicito tramite JpaRepository e Hibernate
        //se l'ID è null viene creato un nuovo record, altrimenti viene
        //aggiornato quello esistente
        return clienteRepository.save(cliente); 
    }
    
    /**
     * Aggiorna un cliente esistente
     */
    public Cliente updateCliente(Long id, Cliente clienteAggiornato) {
        return clienteRepository.findById(id)
            .map(cliente -> {
                //Verifica email duplicata (se diversa da quella attuale)
                if (!cliente.getEmail().equals(clienteAggiornato.getEmail()) && 
                    clienteRepository.findByEmail(clienteAggiornato.getEmail()).isPresent()) {
                    throw new RuntimeException("Email già esistente: " + clienteAggiornato.getEmail());
                }
                
                //Aggiorna i campi
                cliente.setNome(clienteAggiornato.getNome());
                cliente.setCognome(clienteAggiornato.getCognome());
                cliente.setIndirizzo(clienteAggiornato.getIndirizzo());
                cliente.setLocalita(clienteAggiornato.getLocalita());
                cliente.setComune(clienteAggiornato.getComune());
                cliente.setProvincia(clienteAggiornato.getProvincia());
                cliente.setEmail(clienteAggiornato.getEmail());
                cliente.setNote(clienteAggiornato.getNote());
                
                return clienteRepository.save(cliente);
            })
            .orElseThrow(() -> new RuntimeException("Cliente non trovato con ID: " + id));
    }
    
    /**
     * Elimina un cliente
     */
    public void deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente non trovato con ID: " + id);
        }
    }
    
    /**
     * Ricerca clienti per nome e cognome (case insensitive)
     */
    public List<Cliente> searchClienti(String nome, String cognome) {
        if (nome == null) nome = "";
        if (cognome == null) cognome = "";
        return clienteRepository.findByNomeAndCognomeContainingIgnoreCase(nome, cognome);
    }
    
    /**
     * Ricerca clienti per comune
     */
    public List<Cliente> getClientiByComune(String comune) {
        return clienteRepository.findByComuneContainingIgnoreCase(comune);
    }
}