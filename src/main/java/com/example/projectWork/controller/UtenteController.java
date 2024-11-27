package com.example.projectWork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.projectWork.dto.UtenteDTO;
import com.example.projectWork.service.UtenteService;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    // Creazione di un nuovo utente
    @PostMapping
    public ResponseEntity<UtenteDTO> createUtente(@RequestBody UtenteDTO utenteDTO) {
        UtenteDTO createdUtente = utenteService.createUtente(utenteDTO);
        return new ResponseEntity<>(createdUtente, HttpStatus.CREATED);
    }

    // Recupero di tutti gli utenti
    @PostMapping("/all")
    public ResponseEntity<List<UtenteDTO>> getAllUtenti() {
        List<UtenteDTO> utenti = utenteService.getAllUtenti();
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<UtenteDTO> getUtenteByUsernameAndPassword(@RequestBody UtenteDTO utenteDTO) {
        try {
            UtenteDTO utente = utenteService.getUtenteByUsernameAndPassword(utenteDTO);
            return new ResponseEntity<>(utente, HttpStatus.OK);
            
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Utente non trovato
        }
    }

    // Recupero di un utente per username
    @GetMapping("/{username}")
    public ResponseEntity<UtenteDTO> getUtenteByUsername(@PathVariable String username) {
        try {
            UtenteDTO utente = utenteService.getUtenteByUsername(username);
            return new ResponseEntity<>(utente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Utente non trovato
        }
    }

    // Aggiornamento dei dati di un utente
    @PutMapping("/{username}")
    public ResponseEntity<UtenteDTO> updateUtente(@PathVariable String username, @RequestBody UtenteDTO utenteDTO) {
        try {
            UtenteDTO updatedUtente = utenteService.updateUtente(username, utenteDTO);
            return new ResponseEntity<>(updatedUtente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Utente non trovato
        }
    }

    // Eliminazione di un utente
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUtente(@PathVariable String username) {
        boolean isDeleted = utenteService.deleteUtente(username);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // Utente non trovato
    }
}
