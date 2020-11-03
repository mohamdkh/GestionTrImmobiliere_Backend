package com.example.GestionDesTransactionsImmobilieres.Dao;

import com.example.GestionDesTransactionsImmobilieres.Entities.Piece_justificative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Piece_justifRepository extends  JpaRepository<Piece_justificative, Integer> {
}
