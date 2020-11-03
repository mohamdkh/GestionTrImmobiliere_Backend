package com.example.GestionDesTransactionsImmobilieres.Dao;

import com.example.GestionDesTransactionsImmobilieres.Entities.Type_bien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeBienRepository extends JpaRepository<Type_bien,Integer> {
}
