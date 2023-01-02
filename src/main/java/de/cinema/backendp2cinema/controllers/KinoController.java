package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Kino;
import de.cinema.backendp2cinema.exceptions.KinoNotFoundException;
import de.cinema.backendp2cinema.repositories.KinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/kino")
public class KinoController {
    
    KinoRepository kinoRepository;

    @Autowired
    public KinoController(KinoRepository kinoRepository) {
        this.kinoRepository = kinoRepository;
    }

    //alle Kinos wiedergeben
    @GetMapping
    public ResponseEntity<Iterable<Kino>> findAll(){
        
        Iterable<Kino> kinos = kinoRepository.findAll();
        return new ResponseEntity<>(kinos, HttpStatus.OK);
    }

    //Kino nach ID wiedergeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Kino> suche = kinoRepository.findById(id);
        try {
            Kino gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(new KinoNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Film speichern
    @PostMapping("/add")
    public ResponseEntity<Kino> save(@RequestBody Kino newKino){
        Kino addedKino = kinoRepository.save(newKino);
        return new ResponseEntity<>(addedKino, HttpStatus.CREATED);
    }

    //Film ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Kino kino) {
        Optional<Kino> toUpdate = kinoRepository.findById(id);

        try {
            UUID kinoId = toUpdate.get().getId();
            kino.setId(kinoId);
            kinoRepository.save(kino);
            return new ResponseEntity<>(kino, HttpStatus.OK);

        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<>(new KinoNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Film nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Kino> toDelete = kinoRepository.findById(id);
        try {
            kinoRepository.deleteById(toDelete.get().getId());
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new KinoNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
