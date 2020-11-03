package com.example.GestionDesTransactionsImmobilieres.Dao;

import com.example.GestionDesTransactionsImmobilieres.Entities.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande,Integer> {
}
