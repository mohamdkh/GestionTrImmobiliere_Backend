package com.example.GestionDesTransactionsImmobilieres.Dao;

import com.example.GestionDesTransactionsImmobilieres.Entities.Type_operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOperationRepository extends JpaRepository<Type_operation,Integer> {
}
