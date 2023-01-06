package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Sitzplan;
import de.cinema.backendp2cinema.exceptions.SitzplanNotFoundException;
import de.cinema.backendp2cinema.repositories.SitzplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sitzplan")
public class SitzplanController {

    SitzplanRepository sitzplanRepository;

    @Autowired
    public SitzplanController(SitzplanRepository sitzplanRepository) {
        this.sitzplanRepository = sitzplanRepository;
    }

    //alle Sitzpläne zurückgeben
    @GetMapping
    public ResponseEntity<Iterable<Sitzplan>> findAll(){

        Iterable<Sitzplan> sitze = sitzplanRepository.findAll();
        return new ResponseEntity<>(sitze, HttpStatus.OK);
    }

    //Sitzplan nach ID zurückgeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Sitzplan> suche = sitzplanRepository.findById(id);
        try {
            Sitzplan gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(new SitzplanNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Sitzplan hinzufügen
    @PostMapping("/add")
    public ResponseEntity<Sitzplan> save(@RequestBody Sitzplan newSitzplan){
        Sitzplan addedSitzplan = sitzplanRepository.save(newSitzplan);
        return new ResponseEntity<>(addedSitzplan, HttpStatus.CREATED);
    }

    //Sitzplan ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Sitzplan sitzplan) {
        Optional<Sitzplan> toUpdate = sitzplanRepository.findById(id);

        try {
            UUID sitzplanId = toUpdate.get().getId();
            sitzplan.setId(sitzplanId);
            sitzplanRepository.save(sitzplan);
            return new ResponseEntity<>(sitzplan, HttpStatus.OK);

        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<>(new SitzplanNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Sitzplan nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Sitzplan> toDelete = sitzplanRepository.findById(id);
        try {
            UUID sitzplanId = toDelete.get().getId();
            sitzplanRepository.deleteById(sitzplanId);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SitzplanNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
