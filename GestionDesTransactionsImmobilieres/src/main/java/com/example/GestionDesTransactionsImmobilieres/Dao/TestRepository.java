package com.example.GestionDesTransactionsImmobilieres.Dao;

import com.example.GestionDesTransactionsImmobilieres.Entities.annonces;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository   extends JpaRepository<annonces, Long> {
}
