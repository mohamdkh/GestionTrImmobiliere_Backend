package com.example.GestionDesTransactionsImmobilieres.Dao;

import com.example.GestionDesTransactionsImmobilieres.Entities.Intermediaire;
import com.example.GestionDesTransactionsImmobilieres.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IntermediaireRepository extends JpaRepository<Intermediaire,Integer> {
    @Query(value ="SELECT i from Intermediaire i where  i.id_user= :id")
    public Intermediaire FindAccountIntermmediaire(@Param("id") int id);
}
