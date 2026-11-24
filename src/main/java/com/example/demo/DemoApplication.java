package com.example.demo;

import com.example.demo.entities.Compte;
import com.example.demo.entities.TypeCompte;
import com.example.demo.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(CompteRepository compteRepository) {
        return args -> {
            compteRepository.save(new Compte(null, 4000.0, new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, 7500.0, new Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null, 12000.0, new Date(), TypeCompte.COURANT));
            System.out.println("Données de test initialisées avec succès !");
        };
    }
}
