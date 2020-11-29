package com.example.GestionDesTransactionsImmobilieres.Entities;

import lombok.Getter;
import lombok.Setter;

public class UserInfos {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String nom;
    @Getter
    @Setter
    private String prenom;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String tel;
    @Getter @Setter
    private String commune1;
    @Getter @Setter
    private String commune2;
    @Getter @Setter
    private String commune3;
    @Getter @Setter
    private int id_user;
    @Getter @Setter
    private int id_piece_justif;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String adresse;
    @Getter @Setter
    private String role;
    @Getter @Setter
    private String status;
}
