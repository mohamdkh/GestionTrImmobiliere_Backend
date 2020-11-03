package com.example.GestionDesTransactionsImmobilieres.Entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class photo_bien {
    public photo_bien(byte[] image,String extension){
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
    private Long id;
    @Getter
    @Setter
    @Column(name = "picture", length = 1000)
    @Lob
    private byte[] image;
    @Getter @Setter
    private String FullName;

    public photo_bien() {
        super();
    }

}