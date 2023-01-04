package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Rabatt;
import de.cinema.backendp2cinema.exceptions.RabattNotFoundException;
import de.cinema.backendp2cinema.repositories.RabattRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rabatt")
public class RabattController {

    RabattRepository rabattRepository;

    @Autowired
    public RabattController(RabattRepository rabattRepository) {
        this.rabattRepository = rabattRepository;
    }

    //alle Rabatte zurückgeben
    @GetMapping
    public ResponseEntity<Iterable<Rabatt>> findAll(){
        Iterable<Rabatt> rabatte = rabattRepository.findAll();
        return new ResponseEntity<>(rabatte, HttpStatus.OK);
    }

    //Rabatt nach ID zurückgeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Rabatt> suche = rabattRepository.findById(id);
        try{
            Rabatt gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new RabattNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Rabatt hinzufügen
    @PostMapping("/add")
    public ResponseEntity<Rabatt> save(@RequestBody Rabatt newRabatt){
        Rabatt addedRabatt = rabattRepository.save(newRabatt);
        return new ResponseEntity<>(addedRabatt, HttpStatus.CREATED);
    }

    //Rabatt ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Rabatt rabatt) {
        Optional<Rabatt> toUpdate = rabattRepository.findById(id);

        try {
            UUID rabattId = toUpdate.get().getId();
            rabatt.setId(rabattId);
            rabattRepository.save(rabatt);
            return new ResponseEntity<>(rabatt, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new RabattNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Rabatt nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Rabatt> toDelete = rabattRepository.findById(id);
        try{
            UUID rabattId = toDelete.get().getId();
            rabattRepository.deleteById(rabattId);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new RabattNotFoundException(id), HttpStatus.NOT_FOUND);
        }
    }

}

