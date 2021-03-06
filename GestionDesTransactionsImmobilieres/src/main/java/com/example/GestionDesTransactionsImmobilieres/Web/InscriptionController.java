package com.example.GestionDesTransactionsImmobilieres.Web;

import com.example.GestionDesTransactionsImmobilieres.Entities.Intermediaire;
import com.example.GestionDesTransactionsImmobilieres.Entities.UserInfos;
import com.example.GestionDesTransactionsImmobilieres.Services.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Register/")
public class InscriptionController {
    @Autowired
    private InscriptionService service;
    @PostMapping("/postData")
    public void Register(@RequestBody UserInfos userInfos)  {
        service.RegisterUser(userInfos);
    }
    @GetMapping(path = { "/GetDemandes" })
    public List<Intermediaire> GetDemandes(@RequestParam("status") String status) throws IOException {
        return service.GetDemandeAdhesion(status);
    }
    @GetMapping(path = {"/Changestatus" })
    public void StatusDemandeAdhesion(@RequestParam("id") int id,@RequestParam("status") String status) throws IOException {
           
         service.StatusDemandeAdhesion(id,status);
    }
    @GetMapping(path = {"/GetUserInfos" })
    public UserInfos GetUserInfos(@RequestParam("id") String id) throws IOException {

        return service.GetInfosIntermmediaire(Integer.parseInt(id));
    }
}
