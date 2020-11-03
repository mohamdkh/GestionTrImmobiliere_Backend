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
    private Long id;
    @Getter @Setter
    private String type_bien;
    @Getter @Setter @Column(name = "type_ops")
    private String type_operation;
    @Getter @Setter
    private float surface;
    @Getter @Setter
    private float prix;
    @Getter @Setter
    @Column(name = "descr")
    private  String description;
    @Getter @Setter
    private int id_photo;
    @Getter @Setter
    private int lat;
    @Getter @Setter
    private int lon;
    @Getter @Setter
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    @Getter @Setter @Column(name = "id_piece_j")
    private int id_piece_justif;
    @Getter @Setter @Column(name = "nomcomplet")
    private String nom_complet_proprietaire;
    @Getter @Setter
    private String telephone;
    @Getter @Setter
    private String email;

    public annonces(String type_bien, String type_operation, float surface, float prix, String description, int id_photo, int lat, int lon, Date date, int id_piece_justif, String nom_complet_proprietaire, String telephone, String email) {
        this.type_bien = type_bien;
        this.type_operation = type_operation;
        this.surface = surface;
        this.prix = prix;
        this.description = description;
        this.id_photo = id_photo;
        this.lat = lat;
        this.lon = lon;
        this.date = date;
        this.id_piece_justif = id_piece_justif;
        this.nom_complet_proprietaire = nom_complet_proprietaire;
        this.telephone = telephone;
        this.email = email;
    }

    public annonces() {

    }
}
