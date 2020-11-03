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
    int id;
    @Getter
    @Setter
    private String Nom;
    @Getter @Setter
    private String Prenom;
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
}
