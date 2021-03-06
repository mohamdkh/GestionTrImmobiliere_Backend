package com.example.GestionDesTransactionsImmobilieres.Dao;

import com.example.GestionDesTransactionsImmobilieres.Entities.annonces;
import com.example.GestionDesTransactionsImmobilieres.Entities.communes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AnnoncesRepository extends JpaRepository<annonces,Long> {
    @Query(value = "SELECT commune from annonces annonce where  annonce.type_bien =:type_bien and  annonce.type_operation =:type_ops")
    List<annonces> ListAnnoncesByType(@Param("type_bien") long type_bien, @Param("type_ops") long type_ops);
    @Query(value = "SELECT count(*) from annonces Group by type_bien order by type_bien")
	public List<Integer> CountByTypeBien();
    @Query(value = "SELECT count(*) from annonces Group by type_operation order by type_operation")
   	public List<Integer> CountByTypeOps();
    @Query(value = "SELECT annonce  from annonces annonce  where annonce.ref like :ime  order by date DESC")
   	public List<annonces> CountimeAnnonce(@Param("ime") String ime);
}
