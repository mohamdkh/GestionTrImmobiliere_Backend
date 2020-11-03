package com.example.GestionDesTransactionsImmobilieres.Dao;

import com.example.GestionDesTransactionsImmobilieres.Entities.annonces;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnoncesRepository extends JpaRepository<annonces,Long> {
}
