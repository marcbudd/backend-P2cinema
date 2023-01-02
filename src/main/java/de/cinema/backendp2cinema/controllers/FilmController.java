package de.cinema.backendp2cinema.controllers;

import de.cinema.backendp2cinema.entities.Film;
import de.cinema.backendp2cinema.exceptions.FilmNotFoundException;
import de.cinema.backendp2cinema.exceptions.KinoNotFoundException;
import de.cinema.backendp2cinema.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/film")
public class FilmController {

    FilmRepository filmRepository;

    @Autowired
    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    //alle Filme zurückgeben
    @GetMapping
    public ResponseEntity<Iterable<Film>> findAll(){
        Iterable<Film> filme = filmRepository.findAll();
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }

    //Film nach ID zurückgeben
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        Optional<Film> suche = filmRepository.findById(id);
        try{
            Film gefunden = suche.get();
            return new ResponseEntity<>(gefunden, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new FilmNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Film hinzufügen
    @PostMapping("/add")
    public ResponseEntity<Film> save(@RequestBody Film newFilm){
        Film addedFilm = filmRepository.save(newFilm);
        return new ResponseEntity<>(addedFilm, HttpStatus.CREATED);
    }

    //Film ändern
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Film film) {
        Optional<Film> toUpdate = filmRepository.findById(id);

        try {
            UUID filmId = toUpdate.get().getId();
            film.setId(filmId);
            filmRepository.save(film);
            return new ResponseEntity<>(film, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new KinoNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //Film nach ID löschen
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Film> toDelete = filmRepository.findById(id);
        try{
            UUID filmId = toDelete.get().getId();
            filmRepository.deleteById(filmId);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new KinoNotFoundException(id), HttpStatus.NOT_FOUND);
        }
    }

}

