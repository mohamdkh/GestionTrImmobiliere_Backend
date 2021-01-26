package com.example.GestionDesTransactionsImmobilieres.Dao;

import com.example.GestionDesTransactionsImmobilieres.Entities.Demande;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DemandeRepository extends JpaRepository<Demande,Long> {
	  @Query(value ="SELECT demande from Demande demande where  demande.id_annonce =:id ")
	    List<Demande> GetDemandes(@Param("id") long id);
}
