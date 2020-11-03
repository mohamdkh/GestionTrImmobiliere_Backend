package com.example.GestionDesTransactionsImmobilieres.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Demande {
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
    @Getter
    @Setter
    private String Nom_demandeur;
    @Getter
    @Setter
    private String tel;
    @Getter
    @Setter
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Getter
    @Setter
    private Date date;

    public Demande(String nom_demandeur, String tel, String email, Date date) {
        Nom_demandeur = nom_demandeur;
        this.tel = tel;
        this.email = email;
        this.date = date;
    }
    public Demande() {
        super();
    }
}
