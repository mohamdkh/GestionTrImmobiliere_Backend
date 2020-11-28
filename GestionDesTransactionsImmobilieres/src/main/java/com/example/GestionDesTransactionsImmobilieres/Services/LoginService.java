package com.example.GestionDesTransactionsImmobilieres.Services;

import com.example.GestionDesTransactionsImmobilieres.Dao.IntermediaireRepository;
import com.example.GestionDesTransactionsImmobilieres.Dao.UtilisateurRepository;
import com.example.GestionDesTransactionsImmobilieres.Entities.Intermediaire;
import com.example.GestionDesTransactionsImmobilieres.Entities.UserInfos;
import com.example.GestionDesTransactionsImmobilieres.Entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private IntermediaireRepository intermediaireRepository;
    private Utilisateur user;
    private UserInfos userInfos;
    public UserInfos SeConnecter(String email, String mdp){
        user=new Utilisateur();
        userInfos=new UserInfos();
       user=utilisateurRepository.FindAccount(email,mdp);
         Optional<Intermediaire> intermediaire;
       if(user!=null){
           if(user.getRole().equals("admin")){
               userInfos.setEmail(user.getEmail());
               userInfos.setRole(user.getRole());
               userInfos.setPassword(user.getMot_passe());
               userInfos.setStatus(user.getStatut());
           }
           else{
               Intermediaire intermediaire1=intermediaireRepository.FindAccountIntermmediaire(user.getId());
               userInfos.setCommune1(String.valueOf(intermediaire1.getCommune1()));
               userInfos.setCommune2(String.valueOf(intermediaire1.getCommune2()));
               userInfos.setCommune3(String.valueOf(intermediaire1.getCommune3()));
               userInfos.setEmail(intermediaire1.getEmail());
               userInfos.setTel(intermediaire1.getTel());
               userInfos.setNom(intermediaire1.getNom());
               userInfos.setPrenom(intermediaire1.getPrenom());
               userInfos.setId_piece_justif(intermediaire1.getId_piece_justif());
               userInfos.setPassword(user.getMot_passe());
               userInfos.setStatus(user.getStatut());
               userInfos.setRole(user.getRole());
           }
           userInfos.setId(user.getId());
           return userInfos;
       }
       return null;
    }

}
