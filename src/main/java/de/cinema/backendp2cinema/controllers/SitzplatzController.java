package de.cinema.backendp2cinema.controllers;

import de.cinema.backendp2cinema.entities.Sitzplatz;
import de.cinema.backendp2cinema.exceptions.SitzplatzNotFoundException;
import de.cinema.backendp2cinema.repositories.SitzplatzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sitzplatz")
public class SitzplatzController {

    SitzplatzRepository sitzplatzRepository;

    @Autowired
    public SitzplatzController(SitzplatzRepository sitzplatzRepository) {
        this.sitzplatzRepository = sitzplatzRepository;
    }

    //alle Sitzplätze zurückgeben
    @GetMapping
    public ResponseEntity<Iterable<Sitzplatz>> findAll(){

        Iterable<Sitzplatz> sitze = sitzplatzRepository.findAll();
        return new ResponseEntity<>(sitze, HttpStatus.OK);
    }

    //Sitzplatz nach ID zurückgeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Sitzplatz> suche = sitzplatzRepository.findById(id);
        try {
            Sitzplatz gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(new SitzplatzNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Sitzplatz hinzufügen
    @PostMapping("/add")
    public ResponseEntity<Sitzplatz> save(@RequestBody Sitzplatz newSitzplatz){
        Sitzplatz addedSitzplatz = sitzplatzRepository.save(newSitzplatz);
        return new ResponseEntity<>(addedSitzplatz, HttpStatus.CREATED);
    }

    //Sitzplatz ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Sitzplatz sitzplatz) {
        Optional<Sitzplatz> toUpdate = sitzplatzRepository.findById(id);

        try {
            UUID sitzplatzId = toUpdate.get().getId();
            sitzplatz.setId(sitzplatzId);
            sitzplatzRepository.save(sitzplatz);
            return new ResponseEntity<>(sitzplatz, HttpStatus.OK);

        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<>(new SitzplatzNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Sitzplatz nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Sitzplatz> toDelete = sitzplatzRepository.findById(id);
        try {
            UUID sitzplatzId = toDelete.get().getId();
            sitzplatzRepository.deleteById(sitzplatzId);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SitzplatzNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
