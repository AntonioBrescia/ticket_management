package com.example.projectWork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projectWork.dto.UtenteDTO;
import com.example.projectWork.entity.Utente;
import com.example.projectWork.repository.UtenteRepository;
import com.example.projectWork.mapper.UtenteMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    private final UtenteMapper utenteMapper = new UtenteMapper(); // Instanzia il mapper
    public UtenteDTO getUtenteByUsername(String username) {
        Utente utente = utenteRepository.findByUsername(username);
        if (utente != null) {
            return utenteMapper.utenteToUtenteDTO(utente); // Usa il mapper per la conversione
        }
        throw new RuntimeException("Utente non trovato");
    }

    // Metodo per ottenere un utente tramite username e pass

    public UtenteDTO getUtenteByUsernameAndPassword(UtenteDTO utenteDTO) {

        Utente utente = utenteRepository.findByUsernameAndPassword(utenteDTO.getUsername(),utenteDTO.getPassword());
        if (utente != null) {
            return utenteMapper.utenteToUtenteDTO(utente); // Usa il mapper per la conversione
        }
        throw new RuntimeException("Utente non trovato");
    }

    // Metodo per creare un nuovo utente
    public UtenteDTO createUtente(UtenteDTO utenteDTO) {
        Utente utente = utenteMapper.utenteDTOToUtente(utenteDTO); // Converte il DTO in entità
        utente = utenteRepository.save(utente);
        return utenteMapper.utenteToUtenteDTO(utente); // Restituisce il DTO
    }

    // Metodo per ottenere tutti gli utenti
    public List<UtenteDTO> getAllUtenti() {
        List<Utente> utenti = utenteRepository.findAll();
        return utenti.stream()
                .map(utenteMapper::utenteToUtenteDTO)
                .collect(Collectors.toList());
    }

    // Metodo per aggiornare un utente
    public UtenteDTO updateUtente(String username, UtenteDTO utenteDTO) {
        Utente utente = utenteRepository.findByUsername(username);
        if (utente != null) {
            utente.setUsername(utenteDTO.getUsername());
            utente.setPassword(utenteDTO.getPassword());
            utente.setAmministratore(utenteDTO.getAmministratore());
            utente = utenteRepository.save(utente);
            return utenteMapper.utenteToUtenteDTO(utente);
        }
        throw new RuntimeException("Utente non trovato");
    }

    // Metodo per eliminare un utente
    public boolean deleteUtente(String username) {
        Utente utente = utenteRepository.findByUsername(username);
        if (utente != null) {
            utenteRepository.delete(utente);
            return true;
        }
        return false; // Utente non trovato
    }
}
