package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Saal;
import de.cinema.backendp2cinema.exceptions.SaalNotFoundException;
import de.cinema.backendp2cinema.repositories.SaalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/saal")
public class SaalController {

    SaalRepository saalRepository;

    @Autowired
    public SaalController(SaalRepository saalRepository) {
        this.saalRepository = saalRepository;
    }

    //alle Säle zurückgeben
    @GetMapping
    public ResponseEntity<Iterable<Saal>> findAll(){

        Iterable<Saal> saele = saalRepository.findAll();
        return new ResponseEntity<>(saele, HttpStatus.OK);
    }

    //Saal nach ID zurückgeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Saal> suche = saalRepository.findById(id);
        try {
            Saal gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(new SaalNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Saal hinzufügen
    @PostMapping("/add")
    public ResponseEntity<Saal> save(@RequestBody Saal newSaal){
        Saal addedSaal = saalRepository.save(newSaal);
        return new ResponseEntity<>(addedSaal, HttpStatus.CREATED);
    }

    //Saal ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Saal saal) {
        Optional<Saal> toUpdate = saalRepository.findById(id);

        try {
            UUID saalId = toUpdate.get().getId();
            saal.setId(saalId);
            saalRepository.save(saal);
            return new ResponseEntity<>(saal, HttpStatus.OK);

        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<>(new SaalNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Saal nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Saal> toDelete = saalRepository.findById(id);
        try {
            UUID saalId = toDelete.get().getId();
            saalRepository.deleteById(saalId);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SaalNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
