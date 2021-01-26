package com.example.GestionDesTransactionsImmobilieres.Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GestionDesTransactionsImmobilieres.Dao.AnnoncesRepository;
import com.example.GestionDesTransactionsImmobilieres.Dao.DemandeRepository;
import com.example.GestionDesTransactionsImmobilieres.Dao.TypeBienRepository;
import com.example.GestionDesTransactionsImmobilieres.Dao.TypeOperationRepository;
import com.example.GestionDesTransactionsImmobilieres.Entities.Statistique;

@Service
public class StatistiqueService {
	private List<Statistique> listStatistiques;
	@Autowired
	private AnnoncesRepository annoncesRepository;
	@Autowired
	private TypeBienRepository typeBienRepository;
	@Autowired
	private TypeOperationRepository typeOperationRepository;
	@Autowired
	private DemandeRepository demandeRepository;
	private final int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
	private int somme=0;

	public List<Statistique> StatJournaliereAnnonce(long typebien,long typeops) {
		listStatistiques = new ArrayList<Statistique>();
		List<Integer> list=new ArrayList<Integer>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
				for (int i = 0; i < 7; i++) {
					somme=0;
					String dayIterator = format.format(date.getTime() - i * MILLIS_IN_DAY);
					annoncesRepository.findAll().forEach(annonce -> {
						//statistique spécifique par type
						if (format.format(annonce.getDate()).equals(dayIterator) && typeops==annonce.getType_operation() && typebien==annonce.getType_bien()) {
							somme++;
						}
						//statistique générale
						else if(format.format(annonce.getDate()).equals(dayIterator) && typeops==0 &&  typeops==0 ) {
							somme++;
						}
					});
					list.add(somme);
					System.out.println(somme+"++"+dayIterator);
					
				}
				Statistique statistique=new Statistique();
				statistique.setType("Evolution journalière des annonces");
				statistique.setListstatistiques(list);
				listStatistiques.add(statistique);
		return listStatistiques;
	}
	public List<Statistique> StatMonsuelAnnonce(long typebien,long typeops) {
		listStatistiques = new ArrayList<Statistique>();
		List<Integer> list=new ArrayList<Integer>();
		Date date = new Date();
				for (int i = 0; i < 12; i++) {
					Date monthDate=new Date(date.getYear(), i, 15);
					somme=0;
					annoncesRepository.findAll().forEach(annonce -> {
						//statistique spécifique par type
						if (annonce.getDate().getMonth()==monthDate.getMonth() && annonce.getDate().getYear()==monthDate.getYear() && typeops==annonce.getType_operation() && typebien==annonce.getType_bien()) {
							somme++;
						}
						//statistique générale
						else if(annonce.getDate().getMonth()==monthDate.getMonth() && annonce.getDate().getYear()==monthDate.getYear() && typeops==0 &&  typeops==0 ) {
							somme++;
						}
					});
					list.add(somme);
					
				}
				Statistique statistique=new Statistique();
				statistique.setType("Evolution monsuelle des annonces");
				statistique.setListstatistiques(list);
				listStatistiques.add(statistique);
		return listStatistiques;
	}
	public List<Statistique> StatJournaliereDemande() {
		listStatistiques = new ArrayList<Statistique>();
		List<Integer> list=new ArrayList<Integer>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
				for (int i = 0; i < 7; i++) {
					somme=0;
					String dayIterator = format.format(date.getTime() - i * MILLIS_IN_DAY);
					demandeRepository.findAll().forEach(demande -> {
						//statistique spécifique par type
						if (format.format(demande.getDate()).equals(dayIterator) ) {
							somme++;
						}
					});
					list.add(somme);
					
				}
				Statistique statistique=new Statistique();
				statistique.setType("Evolution journalière des demandes");
				statistique.setListstatistiques(list);
				listStatistiques.add(statistique);
		return listStatistiques;
	}
	public List<Statistique> StatMonsuelDemande() {
		listStatistiques = new ArrayList<Statistique>();
		List<Integer> list=new ArrayList<Integer>();
		Date date = new Date();
				for (int i = 0; i < 12; i++) {
					Date monthDate=new Date(date.getYear(), i, 15);
					somme=0;
					demandeRepository.findAll().forEach(demande -> {
						//statistique spécifique par type
						if (demande.getDate().getMonth()==monthDate.getMonth() && demande.getDate().getYear()==monthDate.getYear() ) {
							somme++;
						}
					});
					list.add(somme);
					
				}
				Statistique statistique=new Statistique();
				statistique.setType("Evolution monsuelle des demandes");
				statistique.setListstatistiques(list);
				listStatistiques.add(statistique);
		return listStatistiques;
	}
	
	public List<Statistique> StatReaprtition(String categorieType){
		listStatistiques = new ArrayList<Statistique>();
		List<Integer> list=new ArrayList<Integer>();
		if(categorieType.equals("typebien")) {
				list=annoncesRepository.CountByTypeBien();
		}
		else {
				list=annoncesRepository.CountByTypeOps();
		}
		Statistique statistique=new Statistique();
		statistique.setType(categorieType);
		statistique.setListstatistiques(list);
		listStatistiques.add(statistique);
		return listStatistiques;
	}
	
}
