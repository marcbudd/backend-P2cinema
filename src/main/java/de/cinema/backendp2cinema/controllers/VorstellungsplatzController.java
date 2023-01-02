package de.cinema.backendp2cinema.controllers;
import de.cinema.backendp2cinema.entities.Vorstellungsplatz;
import de.cinema.backendp2cinema.exceptions.VorstellungsplatzNotFoundException;
import de.cinema.backendp2cinema.repositories.VorstellungsplatzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vorstellungsplatz")
public class VorstellungsplatzController {

    VorstellungsplatzRepository vorstellungsplatzRepository;

    @Autowired
    public VorstellungsplatzController(VorstellungsplatzRepository vorstellungsplatzRepository) {
        this.vorstellungsplatzRepository = vorstellungsplatzRepository;
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

    //Vorstellungsplatz hinzufügen
    @PostMapping("/add")
    public ResponseEntity<Vorstellungsplatz> save(@RequestBody Vorstellungsplatz newVorstellungsplatz){
        Vorstellungsplatz addedVorstellungsplatz = vorstellungsplatzRepository.save(newVorstellungsplatz);
        return new ResponseEntity<>(addedVorstellungsplatz, HttpStatus.CREATED);
    }

    //Vorstellungsplatz ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Vorstellungsplatz vorstellungsplatz) {
        Optional<Vorstellungsplatz> toUpdate = vorstellungsplatzRepository.findById(id);

        try {
            UUID vorstellungsplatzId = toUpdate.get().getId();
            vorstellungsplatz.setId(vorstellungsplatzId);
            vorstellungsplatzRepository.save(vorstellungsplatz);
            return new ResponseEntity<>(vorstellungsplatz, HttpStatus.OK);

        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<>(new VorstellungsplatzNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Vorstellungsplatz nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Vorstellungsplatz> toDelete = vorstellungsplatzRepository.findById(id);
        try {
            UUID vorstellungsplatzId = toDelete.get().getId();
            vorstellungsplatzRepository.deleteById(vorstellungsplatzId);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new VorstellungsplatzNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
