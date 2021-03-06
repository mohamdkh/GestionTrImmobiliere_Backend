package com.example.GestionDesTransactionsImmobilieres.Services;

import com.example.GestionDesTransactionsImmobilieres.Dao.*;

import com.example.GestionDesTransactionsImmobilieres.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnoncesService {
    @Autowired
    AffectationRepository affectationRepository;
    @Autowired
    IntermediaireRepository intermediaireRepository;
    @Autowired
    private DemandeRepository demandeRepository;
    @Autowired
   private  AnnoncesRepository annoncesRepository;
    @Autowired
    private TypeBienRepository typeBienRepository;
    @Autowired
    private TypeOperationRepository typeOperationRepository;
    @Autowired
    private Photo_bienRepository photobienRepos;
    @Autowired
    private JavaMailSender javaMailSender;
    private Intermediaire userIntermediaire;
    private List<annonces> listannonces;
    public List<Type_bien> GetAllTypeBien(){
        return typeBienRepository.findAll();
    }
    public List<Type_operation> GetAllTypeOperation(){
        return  typeOperationRepository.findAll();
    }
    public long PostAnnonceData(annonces annonce){
        annonces annonceData=annonce;
        annonceData.setDate(new Date());
        if(annonce.getRef()!=null) {
        	annonceData.setRef(annonce.getRef());
        }
        else {
        	annonceData.setRef("web");
        }
        annonceData.setStatus("NV");
        annonceData= annoncesRepository.save(annonceData);
        return annonceData.getId();
            
    }
    public List<annonces> GetAnnoncesNV (int id_intermmediaire){
        listannonces=new ArrayList<annonces>();
        annoncesRepository.findAll().forEach(item->{
            if(this.AnnoneIsInCommune(id_intermmediaire,item.getCommune())==true && item.getStatus().equals("NV"))
                 listannonces.add(item);
        });
        return listannonces;
    }
    public annonces GetAnnonce(long id_annonce){
        Optional<annonces> annonce= annoncesRepository.findById(id_annonce);
        if(annonce.isPresent()){
            return annonce.get();
        }
        return new annonces();
    }
    private boolean AnnoneIsInCommune(int id_intermmediaire,int commune){
        userIntermediaire=new Intermediaire();
        intermediaireRepository.findAll().forEach(intermediaire -> {
            if(intermediaire.getId_user()==id_intermmediaire){
                userIntermediaire=intermediaire;
            }
        });
           if(userIntermediaire.getCommune1()==commune ||
                   userIntermediaire.getCommune2()==commune ||
                   userIntermediaire.getCommune3()==commune ){
               return true;
           }

       return false;
    }
    public List<annonces> GetAnnoncesIntermmediaire (long id_intermmediaire) {
        listannonces=new ArrayList<annonces>();
        affectationRepository.findAll().forEach(affectation->{
            if(affectation.getId_intermediaire()==id_intermmediaire){
                Optional<annonces> annonce=annoncesRepository.findById(affectation.getId_annonce());
                if(annonce.isPresent() && annonce.get().getStatus().equals("Accept")){
                    listannonces.add(annonce.get());
                }
            }
        });
        return listannonces;
    }
    public List<annonces> GetAllAnnonces () {
        return annoncesRepository.findAll();
    }
    public List<annonces> GetAcceptsAnnonces () {
        return annoncesRepository.findAll().stream().filter(
                c->c.getStatus().equals("Accept")
        ).collect(Collectors.toList());
    }
    public List<annonces> GetAnnonceIME (String ime) {
        return annoncesRepository.CountimeAnnonce(ime);
    }
    public boolean changeStatuTAnnonce(long id_annonce,String status){
        Optional<annonces> annonceExisting=annoncesRepository.findById(id_annonce);
        if(annonceExisting.isPresent()){
            annonces annonce=annonceExisting.get();
            annonce.setStatus(status);
            annoncesRepository.save(annonce);
            return true;
        }
        return false;
    }
    public boolean AffectationAnnonceToInterm(AffectationAnnonce affectation){
        affectationRepository.save(affectation);
        return this.changeStatuTAnnonce(affectation.getId_annonce(),"Accept");
    }
    public boolean SendDemande(Demande demande){
        demande.setDate(new Date());
        demande.setStatus("en cours");
        demandeRepository.save(demande);
        return true;
    }
    
    
//    public boolean SaveData() {
//    	List<annonces> list=new ArrayList<annonces>();
//    	for(int i=0;i<50;i++) {
//    		if(i<25)
//    		 list.add(new annonces(i/10+1, i/17+1, 500+i, 5000-i, "Description N° "+i, i, i, new Date(), "Utilisateur N°"+i, "+21264207839"+i,"Utilisateur"+i+"@gmail.com" , "NV",300));
//    		else
//       		 list.add(new annonces(i/10+1, i/17+1, 500+i, 5000-i, "Description N° "+i, i, i, new Date(), "Utilisateur N°"+i, "+21264207839"+i,"Utilisateur"+i+"@gmail.com" , "NV",14));
//    	}
//    	annoncesRepository.saveAll(list);
//    	return true;
//    }
    public List<Demande> GetDemandeAnnonce(long id){
    	return demandeRepository.GetDemandes(id);
    }
    public boolean ValiderDemande(long id) {
    	Optional<Demande> demande=demandeRepository.findById(id);
    	if(demande.isPresent()) {
    		String msg="Bonjour Mohamed \n nous avons le plaisir de vous informer que votre demande sur le site GestionTransact"
    				+" posté en "+demande.get().getDate()+" a été bien validé \n "
    				+"Vous recevrez prochainement un appel de l'un de nos intermédiaire \n"
    				+"Nous vous prions de croire à l'expression de notre considération distinguée.\r\n";
    				System.out.println(demande.get().getEmail());
    				SendNotification(demande.get().getEmail(), msg);
    				Demande postDemande=demande.get();
    				postDemande.setStatus("valide");
    				demandeRepository.save(postDemande);
    	}
    	return true;
    }
    
   
    private  void SendNotification(String email,String msg) {
    	try {
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("khadijaouhsaine2020@gmail.com");
        mail.setSubject("Validation de votre demande sur le site GestionTransact");
        mail.setText(msg);
        javaMailSender.send(mail);
    	}catch(Exception e) {
    		System.out.println(e.getCause());
    	}
    }
}
