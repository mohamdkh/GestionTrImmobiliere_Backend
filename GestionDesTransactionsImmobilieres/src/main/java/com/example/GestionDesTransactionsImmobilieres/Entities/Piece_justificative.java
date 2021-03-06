package com.example.GestionDesTransactionsImmobilieres.Entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Piece_justificative {
    public Piece_justificative(byte[] image,String extension){
        this.FullName=extension;
        this.image=image;
    }
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
    private int id;
    @Getter @Setter
    @Column(name = "picture", length = 1000)
    @Lob
    private byte[] image;
    @Getter @Setter
    private String extension;
    @Getter @Setter
    private String FullName;

    public Piece_justificative() {
        super();
    }
    public Piece_justificative(byte[] image,String extension,String FullName){
        this.extension =extension;
        this.FullName=FullName;
        this.image=image;
    }
}
