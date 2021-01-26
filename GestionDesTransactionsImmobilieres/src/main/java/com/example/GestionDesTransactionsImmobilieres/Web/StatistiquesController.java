package com.example.GestionDesTransactionsImmobilieres.Web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GestionDesTransactionsImmobilieres.Entities.Statistique;
import com.example.GestionDesTransactionsImmobilieres.Services.StatistiqueService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Statistique/")
public class StatistiquesController {
	
	 @Autowired
	    private StatistiqueService service;
	  @GetMapping(path = { "/StatJournaliereAnnonce" })
	  public List<Statistique> StatJournaliereAnnonce(@RequestParam("typebien") long typebien,@RequestParam("typeops") long typeops){
		  return service.StatJournaliereAnnonce(typebien,typeops);
	  }
	  @GetMapping(path = { "/StatMonsuelAnnonce" })
	  public List<Statistique> StatMonsuelAnnonce(@RequestParam("typebien") long typebien,@RequestParam("typeops") long typeops){
		  return service.StatMonsuelAnnonce(typebien,typeops);
	  }
	  @GetMapping(path = { "/StatJournaliereDemande" })
	  public List<Statistique> StatJournaliereDemande(){
		  return service.StatJournaliereDemande();
	  }
	  @GetMapping(path = { "/StatMonsuelDemande" })
	  public List<Statistique> StatMonsuelDemande(){
		  return service.StatMonsuelDemande();
	  }
	  @GetMapping(path = { "/StatReaprtition" })
	  public List<Statistique> StatReaprtition(@RequestParam("categorie") String categorie){
		  return service.StatReaprtition(categorie);
	  }
}
