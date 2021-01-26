package com.example.GestionDesTransactionsImmobilieres.Dao;

import com.example.GestionDesTransactionsImmobilieres.Entities.communes;
import com.example.GestionDesTransactionsImmobilieres.Entities.photo_bien;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Photo_bienRepository extends JpaRepository<photo_bien,Long> {
	 @Query(value ="SELECT elem FROM photo_bien elem where elem.id=(select Min(id) from photo_bien elem1 where elem1.id_annonce=elem.id_annonce)")
	 List<photo_bien> ListPhotos();
}

