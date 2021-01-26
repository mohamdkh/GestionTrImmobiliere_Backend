package com.example.GestionDesTransactionsImmobilieres.Entities;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Statistique {
	@Setter @Getter
	private String type;
	@Setter @Getter
	private List<Integer> liststatistiques;
}
