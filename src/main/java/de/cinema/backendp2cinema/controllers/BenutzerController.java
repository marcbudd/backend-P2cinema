package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Benutzer;
import de.cinema.backendp2cinema.exceptions.BenutzerNotFoundException;
import de.cinema.backendp2cinema.repositories.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/benutzer")
public class BenutzerController {

    BenutzerRepository benutzerRepository;

    @Autowired
    public BenutzerController(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    //alle Benutzer zurückgeben
    @GetMapping
    public ResponseEntity<Iterable<Benutzer>> findAll(){
        Iterable<Benutzer> benutzer = benutzerRepository.findAll();
        return new ResponseEntity<>(benutzer, HttpStatus.OK);
    }

    //Benutzer nach ID zurückgeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Benutzer> suche = benutzerRepository.findById(id);
        try{
            Benutzer gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new BenutzerNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Benutzer hinzufügen
    @PostMapping("/add")
    public ResponseEntity<Benutzer> save(@RequestBody Benutzer newBenutzer){
        Benutzer addedBenutzer = benutzerRepository.save(newBenutzer);
        return new ResponseEntity<>(addedBenutzer, HttpStatus.CREATED);
    }

    //Benutzer ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Benutzer benutzer) {
        Optional<Benutzer> toUpdate = benutzerRepository.findById(id);

        try {
            UUID benutzerId = toUpdate.get().getId();
            benutzer.setId(benutzerId);
            benutzerRepository.save(benutzer);
            return new ResponseEntity<>(benutzer, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new BenutzerNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Benutzer nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Benutzer> toDelete = benutzerRepository.findById(id);
        try{
            UUID benutzerId = toDelete.get().getId();
            benutzerRepository.deleteById(benutzerId);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new BenutzerNotFoundException(id), HttpStatus.NOT_FOUND);
        }
    }

}

