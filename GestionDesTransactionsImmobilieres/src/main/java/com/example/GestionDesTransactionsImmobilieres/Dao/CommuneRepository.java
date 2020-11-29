package com.example.GestionDesTransactionsImmobilieres.Dao;

import com.example.GestionDesTransactionsImmobilieres.Entities.communes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommuneRepository extends JpaRepository<communes,Long> {
    @Query(value ="SELECT commune from communes commune where  commune.region =:id ")
    List<communes> ListCommunes(@Param("id") double id);
}
