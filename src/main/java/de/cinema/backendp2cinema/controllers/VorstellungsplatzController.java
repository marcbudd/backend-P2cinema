package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Vorstellung;
import de.cinema.backendp2cinema.entities.Vorstellungsplatz;
import de.cinema.backendp2cinema.exceptions.KeinVorstellungsplatzZuVorstellungException;
import de.cinema.backendp2cinema.exceptions.VorstellungNotFoundException;
import de.cinema.backendp2cinema.exceptions.VorstellungsplatzNotFoundException;
import de.cinema.backendp2cinema.repositories.VorstellungRepository;
import de.cinema.backendp2cinema.repositories.VorstellungsplatzRepository;
import de.cinema.backendp2cinema.services.VorstellungsplatzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vorstellungsplatz")
public class VorstellungsplatzController {

    VorstellungsplatzRepository vorstellungsplatzRepository;
    VorstellungsplatzService vorstellungsplatzService;
    VorstellungRepository vorstellungRepository;

    @Autowired
    public VorstellungsplatzController(VorstellungsplatzRepository vorstellungsplatzRepository, VorstellungsplatzService vorstellungsplatzService, VorstellungRepository vorstellungRepository) {
        this.vorstellungsplatzRepository = vorstellungsplatzRepository;
        this.vorstellungsplatzService = vorstellungsplatzService;
        this.vorstellungRepository = vorstellungRepository;
    }

    //alle Vorstellungsplätze zurückgeben
    @GetMapping
    public ResponseEntity<Iterable<Vorstellungsplatz>> findAll(){

        Iterable<Vorstellungsplatz> vorstellungsplatz = vorstellungsplatzRepository.findAll();
        return new ResponseEntity<>(vorstellungsplatz, HttpStatus.OK);
    }

    //Vorstellungsplatz nach ID zurückgeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Vorstellungsplatz> suche = vorstellungsplatzRepository.findById(id);
        try {
            Vorstellungsplatz gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(new VorstellungsplatzNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Vorstellungsplätze nach Vorstellung zurückgeben
    @GetMapping("findByVorstellung/{id}")
    public ResponseEntity<Object> findByVorstellung(@PathVariable("id") UUID id) {
        Optional<Vorstellung> suche = vorstellungRepository.findById(id);
        Vorstellung gefunden;
        try {
            gefunden = suche.get();
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(new VorstellungNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

        try {
            Optional<List<Vorstellungsplatz>> optionalList = vorstellungsplatzRepository.findAllByVorstellung(gefunden);
            return new ResponseEntity<>(optionalList.get(), HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(new KeinVorstellungsplatzZuVorstellungException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Vorstellungsplatz hinzufügen
//    @PostMapping("/add")
//    public ResponseEntity<Vorstellungsplatz> save(@RequestBody Vorstellungsplatz newVorstellungsplatz){
//        Vorstellungsplatz addedVorstellungsplatz = vorstellungsplatzRepository.save(newVorstellungsplatz);
//        return new ResponseEntity<>(addedVorstellungsplatz, HttpStatus.CREATED);
//    }

    //Vorstellungsplatz ändern
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Vorstellungsplatz vorstellungsplatz) {
//        Optional<Vorstellungsplatz> toUpdate = vorstellungsplatzRepository.findById(id);
//
//        try {
//            UUID vorstellungsplatzId = toUpdate.get().getId();
//            vorstellungsplatz.setId(vorstellungsplatzId);
//            vorstellungsplatzRepository.save(vorstellungsplatz);
//            return new ResponseEntity<>(vorstellungsplatz, HttpStatus.OK);
//
//        }
//        catch(NoSuchElementException e) {
//            return new ResponseEntity<>(new VorstellungsplatzNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
//        }
//
//    }

    //Vorstellungsplatz reservieren
    @PutMapping("/{id}/reservieren")
    public ResponseEntity<Object> reservieren(@PathVariable("id") UUID id){
        return vorstellungsplatzService.reserviereSitz(id);
    }

    //Vorstellungsplatz buchen
    @PutMapping("/{id}/buchen")
    public ResponseEntity<Object> buchen(@PathVariable("id") UUID id){
        return vorstellungsplatzService.bucheSitz(id);
    }

    @PutMapping("/{id}/freigeben")
    public ResponseEntity<Object> freigeben(@PathVariable("id") UUID id){
        return vorstellungsplatzService.befreieSitz(id);
    }

    @PutMapping("/{id}/blockieren")
    public ResponseEntity<Object> blockieren(@PathVariable("id") UUID id){
        return vorstellungsplatzService.blockiereSitz(id);
    }

    //Vorstellungsplatz nach ID löschen
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Object> delete(@PathVariable UUID id) {
//        Optional<Vorstellungsplatz> toDelete = vorstellungsplatzRepository.findById(id);
//        try {
//            UUID vorstellungsplatzId = toDelete.get().getId();
//            vorstellungsplatzRepository.deleteById(vorstellungsplatzId);
//            return new ResponseEntity<>(id, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new VorstellungsplatzNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }


}
