package com.example.GestionDesTransactionsImmobilieres.Dao;

import com.example.GestionDesTransactionsImmobilieres.Entities.Utilisateur;
import com.example.GestionDesTransactionsImmobilieres.Entities.annonces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtilisateurRepository   extends JpaRepository<Utilisateur, Long> {
    @Query(value ="SELECT s from Utilisateur s where  s.email Like :email and s.mot_passe LIKE :password")
    public Utilisateur FindAccount(@Param("email") String email,@Param("password") String password);
}
