package com.example.GestionDesTransactionsImmobilieres.Entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Intermediaire {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Getter @Setter
    long id;
    @Getter
    @Setter
    private String nom;
    @Getter @Setter
    private String prenom;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String tel;
    @Getter @Setter
    private int commune1;
    @Getter @Setter
    private int commune2;
    @Getter @Setter
    private int commune3;
    @Getter @Setter
    private int id_user;
    @Getter @Setter
    private int id_piece_justif;
    @Getter @Setter
    private String status;
    @Getter @Setter
    private String adresse;

    public Intermediaire(String nom, String prenom, String email, String tel, int commune1, int commune2, int commune3, int id_user, int id_piece_justif,String status,String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.commune1 = commune1;
        this.commune2 = commune2;
        this.commune3 = commune3;
        this.id_user = id_user;
        this.id_piece_justif = id_piece_justif;
        this.status=status;
        this.adresse=adresse;
    }

    public Intermediaire() {

    }
}
