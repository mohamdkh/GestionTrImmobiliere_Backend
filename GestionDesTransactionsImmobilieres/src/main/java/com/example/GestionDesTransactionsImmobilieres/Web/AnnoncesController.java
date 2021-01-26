package com.example.GestionDesTransactionsImmobilieres.Web;

import com.example.GestionDesTransactionsImmobilieres.Entities.*;
import com.example.GestionDesTransactionsImmobilieres.Services.AnnoncesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Annonce/")
public class AnnoncesController {
    @Autowired
    private AnnoncesService annoncesService;
    @GetMapping(path = { "/GetTypeOps" })
    public List<Type_operation> GetAllTypeOperation(){
        return annoncesService.GetAllTypeOperation();
    }
    @GetMapping(path = { "/GetTypeBiens" })
    public List<Type_bien> GetAllTypeBien(){
        return annoncesService.GetAllTypeBien();
    }
    @PostMapping("/PostData")
    public long PostData(@RequestBody annonces annonce){
        return annoncesService.PostAnnonceData(annonce);
    }
    @GetMapping(path = { "/GetAnnonces" })
    public List<annonces> GetAnnonces(@RequestParam("idintermmediaire") int id_intermmediaire){
        return annoncesService.GetAnnoncesNV(id_intermmediaire);
    }
    @GetMapping(path = { "/GetAnnonce" })
    public annonces GetAnnonce(@RequestParam("idannonce") int id_annonce){
        return annoncesService.GetAnnonce(id_annonce);
    }
    @GetMapping(path = { "/GetAnnoncesIntermmediaire" })
    public List<annonces> GetAnnoncesIntermmediaire(@RequestParam("idintermmediaire") long id_intermmadiaire){
        return annoncesService.GetAnnoncesIntermmediaire(id_intermmadiaire);
    }
    @GetMapping(path = { "/GetAllAnnonces" })
    public List<annonces> GetAllAnnonces(){
        return annoncesService.GetAllAnnonces();
    }
    @GetMapping(path = { "/GetAcceptsAnnonces" })
    public List<annonces> GetAcceptsAnnonces(){
        return annoncesService.GetAcceptsAnnonces();
    }
    @PostMapping("/Affectation")
    public boolean AffectationAnnonceToInterm(@RequestBody AffectationAnnonce affectation){
        return annoncesService.AffectationAnnonceToInterm(affectation);
    }
    @PostMapping("/ChangeStatusAnnonce")
    public boolean ChangeStatusAnnonce(@RequestBody annonces annonce){
        return annoncesService.changeStatuTAnnonce(annonce.getId(),annonce.getStatus());
    }
    @PostMapping("/SendDemande")
    public boolean SendDemande(@RequestBody Demande demande){
        return annoncesService.SendDemande(demande);
    }
    
    
  @GetMapping(path = { "/getDemandes" })
  public List<Demande> setData(@RequestParam("idannonce") long idannonce){
      return annoncesService.GetDemandeAnnonce(idannonce);
  }
  @GetMapping(path = { "/ValiderDemande" })
  public boolean ValiderDemande(@RequestParam("iddeamande") long iddeamande){
      return annoncesService.ValiderDemande(iddeamande);
  }
  
  @GetMapping(path = { "/GetAnnonceIME" })
  public List<annonces> GetAnnonceIME(@RequestParam("ime") String ime){
      return annoncesService.GetAnnonceIME(ime);
  }



}
