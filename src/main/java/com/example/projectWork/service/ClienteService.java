package com.example.projectWork.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projectWork.dto.ClienteDTO;
import com.example.projectWork.entity.Cliente;
import com.example.projectWork.mapper.ClienteMapper;
import com.example.projectWork.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private final ClienteMapper clienteMapper = new ClienteMapper(); // Mapper per conversione

    // Recupero di tutti i clienti
    public List<ClienteDTO> getAllClienti() {
        List<Cliente> clienti = clienteRepository.findAll();
        return clienti.stream()
                .map(clienteMapper::clienteToClienteDTO) // Usa il mapper per la conversione
                .collect(Collectors.toList());
    }

    // Recupero di un cliente per email
    public ClienteDTO getClienteByEmail(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        // .orElseThrow(() -> new RuntimeException("Cliente con email " + email + " non
        // trovato"));
        return clienteMapper.clienteToClienteDTO(cliente); // Restituisce il DTO
    }

    // Aggiornamento dei dati di un cliente
    public ClienteDTO updateCliente(Integer id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente non trovato"));
        
        // Aggiorna i campi del cliente con i dati del DTO
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setIndirizzo(clienteDTO.getIndirizzo());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setPartitaIva(clienteDTO.getPartitaIva());
        cliente.setCodiceFiscale(clienteDTO.getCodiceFiscale());
        
        cliente = clienteRepository.save(cliente);
        return clienteMapper.clienteToClienteDTO(cliente);
    }

    // Eliminazione di un cliente
    public boolean deleteCliente(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);// .orElse(null);
        if (cliente != null) {
            clienteRepository.delete(cliente);
            return true;
        }
        return false; // Cliente non trovato
    }

    // Metodo per ottenere un Cliente tramite ID
    public ClienteDTO getClienteById(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente non trovato"));
        return clienteMapper.clienteToClienteDTO(cliente); // Usa il mapper per la conversione
    }

    // Metodo per creare un nuovo Cliente
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO); // Converte il DTO in entità
        cliente = clienteRepository.save(cliente);
        return clienteMapper.clienteToClienteDTO(cliente); // Restituisce il DTO
    }

    // Metodo per eliminare un Cliente
    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}