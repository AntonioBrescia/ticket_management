package com.example.projectWork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.projectWork.dto.ClienteDTO;
import com.example.projectWork.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Creazione di un nuovo cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO createdCliente = clienteService.createCliente(clienteDTO);
        return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
    }

    // Recupero di tutti i clienti
    @GetMapping("/all")
    public ResponseEntity<List<ClienteDTO>> getAllClienti() {
        List<ClienteDTO> clienti = clienteService.getAllClienti();
        return new ResponseEntity<>(clienti, HttpStatus.OK);
    }

    // Recupero di un cliente per email
    @GetMapping("/{email}")
    public ResponseEntity<ClienteDTO> getClienteByEmail(@PathVariable String email) {
        try {
            ClienteDTO cliente = clienteService.getClienteByEmail(email);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Cliente non trovato
        }
    }

    // Aggiornamento dei dati di un cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO updatedCliente = clienteService.updateCliente(id, clienteDTO);
            return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Cliente non trovato
        }
    }

    // Eliminazione di un cliente
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCliente(@PathVariable String email) {
        boolean isDeleted = clienteService.deleteCliente(email);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                         : new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Cliente non trovato
    }
}