package com.example.applrest.web;

import com.example.applrest.entities.Personne;
import com.example.applrest.repositories.PersonneRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping
public class PersonController {
    private final PersonneRepository personneRepository;

    public PersonController(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    //API "GET" retournant une liste des objets en format JSON
    @GetMapping("/persons")
    public List<Personne> getPersons() {
        return personneRepository.findAll();
    }

    //API "POST" offrant la possibilité d'ajouter une 1 objet à la base de
    //données en le passant en paramètre.
    @PostMapping("/persons")
    public Personne createPerson(@RequestParam String nom, @RequestParam String prenom, @RequestParam Long age) {
        Personne person = new Personne();
        person.setNom(nom);
        person.setPrenom(prenom);
        person.setAge(age);
        return personneRepository.save(person);
    }

    //1 API "POST" offrant la possibilité d'ajouter une 1 objet à la base de
    //données en générant des valeurs aléatoires
    @PostMapping("/randomPersons")
    public Personne createRandomPersons() {
        Personne person = new Personne();
        person.setNom(UUID.randomUUID().toString());
        person.setPrenom(UUID.randomUUID().toString());
        person.setAge((long) (Math.random()*100));
        return personneRepository.save(person);
    }
}