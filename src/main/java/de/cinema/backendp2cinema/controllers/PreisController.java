package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Preis;
import de.cinema.backendp2cinema.exceptions.PreisNotFoundException;
import de.cinema.backendp2cinema.exceptions.KinoNotFoundException;
import de.cinema.backendp2cinema.repositories.PreisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/preis")
public class PreisController {

    PreisRepository preisRepository;

    @Autowired
    public PreisController(PreisRepository preisRepository) {
        this.preisRepository = preisRepository;
    }

    //alle Preise zurückgeben
    @GetMapping
    public ResponseEntity<Iterable<Preis>> findAll(){
        Iterable<Preis> preise = preisRepository.findAll();
        return new ResponseEntity<>(preise, HttpStatus.OK);
    }

    //Preis nach ID zurückgeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Preis> suche = preisRepository.findById(id);
        try{
            Preis gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new PreisNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Preis hinzufügen
    @PostMapping("/add")
    public ResponseEntity<Preis> save(@RequestBody Preis newPreis){
        Preis addedPreis = preisRepository.save(newPreis);
        return new ResponseEntity<>(addedPreis, HttpStatus.CREATED);
    }

    //Preis ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Preis preis) {
        Optional<Preis> toUpdate = preisRepository.findById(id);

        try {
            UUID preisId = toUpdate.get().getId();
            preis.setId(preisId);
            preisRepository.save(preis);
            return new ResponseEntity<>(preis, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new KinoNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Preis nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Preis> toDelete = preisRepository.findById(id);
        try{
            UUID preisId = toDelete.get().getId();
            preisRepository.deleteById(preisId);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new KinoNotFoundException(id), HttpStatus.NOT_FOUND);
        }
    }

}
