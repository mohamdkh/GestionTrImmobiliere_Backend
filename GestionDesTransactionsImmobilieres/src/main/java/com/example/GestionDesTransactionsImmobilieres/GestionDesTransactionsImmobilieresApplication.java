package com.example.GestionDesTransactionsImmobilieres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionDesTransactionsImmobilieresApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GestionDesTransactionsImmobilieresApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(GestionDesTransactionsImmobilieresApplication.class, args);
	}
}

