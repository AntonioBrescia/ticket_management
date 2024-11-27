package com.example.projectWork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projectWork.entity.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente findByUsernameAndPassword(String username, String password);

    Utente findByUsername(String username);
}
