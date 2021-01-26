package com.example.GestionDesTransactionsImmobilieres.Services;

import com.example.GestionDesTransactionsImmobilieres.Dao.CommuneRepository;
import com.example.GestionDesTransactionsImmobilieres.Entities.communes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommuneService {
    @Autowired
        private CommuneRepository repos;
    List<String> resultat;

    public List<String> getDataCommune(double id) {
        resultat=new ArrayList<String>();
       repos.ListCommunes(id).forEach(elem->{
           resultat.add(elem.getId()+";"+ elem.getLibelle());
       });
       return resultat;
    }

}
