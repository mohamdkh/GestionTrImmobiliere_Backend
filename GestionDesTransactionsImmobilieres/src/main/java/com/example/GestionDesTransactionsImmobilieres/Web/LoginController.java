package com.example.GestionDesTransactionsImmobilieres.Web;

import com.example.GestionDesTransactionsImmobilieres.Entities.UserInfos;
import com.example.GestionDesTransactionsImmobilieres.Entities.Utilisateur;
import com.example.GestionDesTransactionsImmobilieres.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Login/")
public class LoginController  {
    @Autowired
    private LoginService loginService;
    @PostMapping("/Senddata")
    public UserInfos SeConnecter(@RequestBody Utilisateur user){
        System.out.println(user.getEmail());
        return loginService.SeConnecter(user.getEmail(),user.getMot_passe());
    }
    @PostMapping("/ChangePassword")
    public boolean ChangePassword(@RequestBody Utilisateur user){
        return loginService.ChangePassword( user.getId(),user.getMot_passe());
    }
}
