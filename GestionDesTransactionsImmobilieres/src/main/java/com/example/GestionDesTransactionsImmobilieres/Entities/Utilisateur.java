package com.example.GestionDesTransactionsImmobilieres.Entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private int id;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String mot_passe;
    @Getter @Setter
    private String role;
    public Utilisateur(){
        super();
    }
    public Utilisateur(String email, String mot_passe, String role) {
        this.email = email;
        this.mot_passe = mot_passe;
        this.role = role;
    }


}
