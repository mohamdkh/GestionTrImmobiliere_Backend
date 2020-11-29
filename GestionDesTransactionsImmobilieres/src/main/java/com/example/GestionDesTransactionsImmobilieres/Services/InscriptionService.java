package com.example.GestionDesTransactionsImmobilieres.Services;

import com.example.GestionDesTransactionsImmobilieres.Dao.CommuneRepository;
import com.example.GestionDesTransactionsImmobilieres.Dao.IntermediaireRepository;
import com.example.GestionDesTransactionsImmobilieres.Dao.UtilisateurRepository;
import com.example.GestionDesTransactionsImmobilieres.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {
    @Autowired
    private IntermediaireRepository IntermediaireRepos;
    @Autowired
    private UtilisateurRepository  UtilisateurRepos;
    @Autowired
    private CommuneRepository CommuneRepos;
    private JavaMailSender javaMailSender;
    private  List<Intermediaire> listDemandes;
    private UserInfos userinfos;
    private Optional<communes> commune;
    private String[] Regions={"Tanger-Tétouan Al Houceima","Oriental-Rif","Fes-Meknes","Rabat-Salé-Kenitra",
    "BeniMellal-khenifra","Casablanca - Settat","Marrakech - Safi","Drâa‐Tafilalet","Souss-Massa","Guelmim‐Oued Noun",
    "Laayoun Sakia El Hamra","Dakhla Oued Dahab"};
    private int flag;
    private String password;

    public InscriptionService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void RegisterUser(UserInfos userInf){
        Utilisateur user=new Utilisateur();
        user.setEmail(userInf.getEmail());
        user.setMot_passe(userInf.getPassword());
        user.setRole("intermediaire");
        user.setStatut("inactive");
        user =UtilisateurRepos.save(user);
        Intermediaire intermediaire=new Intermediaire(userInf.getNom(),userInf.getPrenom(),userInf.getEmail()
                                                        ,userInf.getTel(),Integer.parseInt(userInf.getCommune1()),Integer.parseInt(userInf.getCommune2()),
                Integer.parseInt(userInf.getCommune3()),user.getId(),userInf.getId_piece_justif(),user.getStatut(),userInf.getAdresse());
        IntermediaireRepos.save(intermediaire);
    }
    public List<Intermediaire> GetDemandeAdhesion(String status){
        this.listDemandes=new ArrayList<Intermediaire>();
        List<Intermediaire> list=IntermediaireRepos.findAll();
        list.forEach(demande->{
            if(demande.getStatus().equals(status))
                this.listDemandes.add(demande);
        });
        return listDemandes;
    }
    public void StatusDemandeAdhesion(int id,String status){
        Optional<Intermediaire> findIntermediate=IntermediaireRepos.findById(id);
        if(findIntermediate.isPresent()){
            Intermediaire intermediaire= findIntermediate.get();
            intermediaire.setStatus(status);
            flag= intermediaire.getId_user();
            UtilisateurRepos.findAll().forEach(elem->{
                if(elem.getId()==flag){
                    Utilisateur currentUser=elem;
                    currentUser.setStatut(status);
                    UtilisateurRepos.save(currentUser);
                    password=currentUser.getMot_passe();
                }
            });
            if(status.equals("active")){
                SendNotification(intermediaire,"Bonjour "+ intermediaire.getNom() +
                        " Votre compte sur la plateforme Gestion des transactions immobilières a été activé avec succès " +
                        "\n vous pouvez consulter votre profil avec :" +
                        "\n Email :"+intermediaire.getEmail()+
                        "\n Mot de passe "+password
                        +"\n Veuillez conserver ces informations. Nous ne serons plus en mesure de vous les fournir à nouveau." );
            }
            else if(status.equals("desactivated")){
                SendNotification(intermediaire,"Bonjour "+ intermediaire.getNom() +
                        " Nous vous remercions de l’intérêt que vous portez à notre Plateforme TransactImmob \n" +
                        "Après lecture attentive de votre profil, nous ne pourrons malheureusement pas y donner une suite favorable"
                         +"\n Bien cordialement,\n" +
                        "\n TransactImmob");
            }
            IntermediaireRepos.save(intermediaire);
        }

    }
    public UserInfos GetInfosIntermmediaire(int id){
        flag=1;
        userinfos=new UserInfos();
        Optional<Intermediaire> findIntermediate=IntermediaireRepos.findById(id);
        if(findIntermediate.isPresent()){
            Intermediaire intermediaire= findIntermediate.get();
            userinfos.setId_user(intermediaire.getId());
            userinfos.setEmail(intermediaire.getEmail());
            userinfos.setTel(intermediaire.getTel());
            userinfos.setNom(intermediaire.getNom());
            userinfos.setPrenom(intermediaire.getPrenom());
            userinfos.setId_piece_justif(intermediaire.getId_piece_justif());
            int[] tab=new int[3];
            tab[0]=intermediaire.getCommune1();
            tab[1]=intermediaire.getCommune2();
            tab[2]=intermediaire.getCommune3();
            String []Communes=new String[3];
            for (int i=0;i<tab.length;i++){
                commune=CommuneRepos.findById((long) tab[i]);
                if(commune.isPresent()){
                    Communes[i]=commune.get().getLibelle();
                    //* Flag sera utilisé pour récupérer le nom de la région
                    flag= (int) commune.get().getRegion();
                }
            }
            userinfos.setCommune1(Communes[0]);
            userinfos.setCommune2(Communes[1]);
            userinfos.setCommune3(Communes[2]);
            System.out.println(flag);
            //* Recuperer la Region
                userinfos.setPassword(Regions[flag-1]);

        }
        return userinfos;
    }



    public void SendNotification(Intermediaire user,String msg) throws MailException {
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("khadijaouhsaine2020@gmail.com");
        mail.setSubject("Vérification de votre inscription");
        mail.setText(msg);
        javaMailSender.send(mail);
    }


}
