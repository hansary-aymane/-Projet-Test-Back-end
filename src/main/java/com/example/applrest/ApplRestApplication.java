package com.example.applrest;

import com.example.applrest.entities.Personne;
import com.example.applrest.repositories.PersonneRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApplRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApplRestApplication.class, args);
    }

    @Autowired
    private PersonneRepository personneRepository;

    @PostConstruct
    public void init() {
        List<Personne> personneList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\H.Aymane\\Desktop\\applREST\\src\\main\\resources\\data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Splitter la ligne par les virgules
                String nom = data[0];
                String prenom = data[1];
                Long age = (long) Integer.parseInt(data[2]);

                Personne person = new Personne();
                person.setNom(nom);
                person.setPrenom(prenom);
                person.setAge(age);

                personneList.add(person);
                personneRepository.save(person);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Gérer les exceptions appropriées
        }
    }
}
