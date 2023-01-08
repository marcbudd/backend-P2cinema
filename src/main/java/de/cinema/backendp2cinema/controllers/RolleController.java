package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Rolle;
import de.cinema.backendp2cinema.exceptions.RolleNotFoundException;
import de.cinema.backendp2cinema.repositories.RolleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rolle")
public class RolleController {

    RolleRepository rolleRepository;

    @Autowired
    public RolleController(RolleRepository rolleRepository) {
        this.rolleRepository = rolleRepository;
    }

    //alle Rollen zurückgeben
    @GetMapping
    public ResponseEntity<Iterable<Rolle>> findAll(){
        Iterable<Rolle> rollen = rolleRepository.findAll();
        return new ResponseEntity<>(rollen, HttpStatus.OK);
    }

    //Rolle nach ID zurückgeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Rolle> suche = rolleRepository.findById(id);
        try{
            Rolle gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new RolleNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Rolle hinzufügen
    @PostMapping("/add")
    public ResponseEntity<Rolle> save(@RequestBody Rolle newRolle){
        Rolle addedRolle = rolleRepository.save(newRolle);
        return new ResponseEntity<>(addedRolle, HttpStatus.CREATED);
    }

    //Rolle ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Rolle rolle) {
        Optional<Rolle> toUpdate = rolleRepository.findById(id);

        try {
            UUID rolleId = toUpdate.get().getId();
            rolle.setId(rolleId);
            rolleRepository.save(rolle);
            return new ResponseEntity<>(rolle, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new RolleNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Rolle nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Rolle> toDelete = rolleRepository.findById(id);
        try{
            UUID rolleId = toDelete.get().getId();
            rolleRepository.deleteById(rolleId);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new RolleNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}

