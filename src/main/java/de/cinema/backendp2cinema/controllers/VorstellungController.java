package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Vorstellung;
import de.cinema.backendp2cinema.exceptions.VorstellungNotFoundException;
import de.cinema.backendp2cinema.repositories.VorstellungRepository;
import de.cinema.backendp2cinema.services.VorstellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vorstellung")
public class VorstellungController {

    VorstellungRepository vorstellungRepository;
    VorstellungService vorstellungService;

    @Autowired
    public VorstellungController(VorstellungRepository vorstellungRepository, VorstellungService vorstellungService) {
        this.vorstellungRepository = vorstellungRepository;
        this.vorstellungService = vorstellungService;
    }

    //alle Vorstellungen zurückgeben
    @GetMapping
    public ResponseEntity<Iterable<Vorstellung>> findAll(){

        Iterable<Vorstellung> vorstellungen = vorstellungRepository.findAll();
        return new ResponseEntity<>(vorstellungen, HttpStatus.OK);
    }

    //Vorstellung nach ID zurückgeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Vorstellung> suche = vorstellungRepository.findById(id);
        try {
            Vorstellung gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(new VorstellungNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Vorstellung hinzufügen
    @PostMapping("/add")
    public ResponseEntity<Vorstellung> save(@RequestBody Vorstellung newVorstellung){
        return vorstellungService.addNeueVorstellung(newVorstellung);
    }

    //Vorstellung ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Vorstellung vorstellung) {
        Optional<Vorstellung> toUpdate = vorstellungRepository.findById(id);

        try {
            UUID vorstellungId = toUpdate.get().getId();
            vorstellung.setId(vorstellungId);
            vorstellungRepository.save(vorstellung);
            return new ResponseEntity<>(vorstellung, HttpStatus.OK);

        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<>(new VorstellungNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Vorstellung nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Vorstellung> toDelete = vorstellungRepository.findById(id);
        try {
            UUID vorstellungId = toDelete.get().getId();
            vorstellungRepository.deleteById(vorstellungId);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new VorstellungNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
