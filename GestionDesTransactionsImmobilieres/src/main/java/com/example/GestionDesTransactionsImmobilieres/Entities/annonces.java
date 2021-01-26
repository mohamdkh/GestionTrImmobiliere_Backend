package com.example.GestionDesTransactionsImmobilieres.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import lombok.Setter;

@Entity
public class annonces {
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
    private Long id;
    @Getter @Setter
    private long type_bien;
    @Getter @Setter @Column(name = "type_ops")
    private long type_operation;
    @Getter @Setter
    private float surface;
    @Getter @Setter
    private float prix;
    @Getter @Setter
    @Column(name = "descr")
    private  String description;
    @Getter @Setter
    private float lat;
    @Getter @Setter
    private float lon;
    @Getter @Setter
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    @Getter @Setter @Column(name = "nomcomplet")
    private String nom_complet_proprietaire;
    @Getter @Setter
    private String telephone;
    @Getter @Setter
    private int commune;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String status;
    @Getter @Setter
    private String ref;

    public annonces(long type_bien, long type_operation, float surface, float prix, String description, float lat, float lon, Date date ,String nom_complet_proprietaire, String telephone, String email,String status,int commune) {
        this.type_bien = type_bien;
        this.type_operation = type_operation;
        this.surface = surface;
        this.prix = prix;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.date = date;
        this.nom_complet_proprietaire = nom_complet_proprietaire;
        this.telephone = telephone;
        this.email = email;
        this.status=status;
        this.commune=commune;
    }

    public annonces() {

    }
}
